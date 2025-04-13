package com.example.onlinequizsystem.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public class DbOperator<T, ID> {
    private final String tableName;
    private final String idColumnName;
    private final Function<ResultSet, T> rowMapper;
    private final Function<T, ID> idExtractor;

    /**
     * Create a new DbOperator
     * @param tableName The database table name
     * @param idColumnName The name of the ID column
     * @param rowMapper Function that converts a ResultSet row to an entity
     * @param idExtractor Function that extracts the ID from an entity
     */
    public DbOperator(String tableName, String idColumnName,
                      Function<ResultSet, T> rowMapper,
                      Function<T, ID> idExtractor) {
        this.tableName = tableName;
        this.idColumnName = idColumnName;
        this.rowMapper = rowMapper;
        this.idExtractor = idExtractor;
    }

    /**
     * Find an entity by its ID
     * @param id The ID to search for
     * @return The entity, or null if not found
     */
    public T findById(ID id) {
        String sql = "SELECT * FROM " + tableName + " WHERE " + idColumnName + " = ?";
        return querySingle(sql, stmt -> setParameter(stmt, 1, id));
    }

    /**
     * Find all entities in the table
     * @return List of all entities
     */
    public List<T> findAll() {
        String sql = "SELECT * FROM " + tableName;
        return queryList(sql, stmt -> {});
    }

    /**
     * Save a new entity
     * @param entity The entity to save
     * @param saveFunction Function to set parameters for the INSERT statement
     * @return true if successful
     */
    public boolean save(T entity, SqlParameterSetter saveFunction) { // issue
        return executeUpdate((String) entity,saveFunction) > 0;
    }

    /**
     * Delete an entity by ID
     * @param id The ID of the entity to delete
     * @return true if successful
     */
    public boolean delete(ID id) {
        String sql = "DELETE FROM " + tableName + " WHERE " + idColumnName + " = ?";
        return executeUpdate(sql, stmt -> setParameter(stmt, 1, id)) > 0;
    }

    // Helper methods below

    /**
     * Execute a query that returns a single entity
     */
    private T querySingle(String sql, SqlParameterSetter paramSetter) {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            paramSetter.setParameters(stmt);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rowMapper.apply(rs);
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } finally {
            DbUtil.closeQuietly(conn);
        }
        return null;
    }

    /**
     * Execute a query that returns a list of entities
     */
    private List<T> queryList(String sql, SqlParameterSetter paramSetter) {
        List<T> results = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            paramSetter.setParameters(stmt);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results.add(rowMapper.apply(rs));
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } finally {
            DbUtil.closeQuietly(conn);
        }
        return results;
    }

    /**
     * Execute an update statement
     */
    public int executeUpdate(String sql, SqlParameterSetter paramSetter) {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            paramSetter.setParameters(stmt);

            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            return 0;
        } finally {
            DbUtil.closeQuietly(conn);
        }
    }

    /**
     * Set a parameter in a PreparedStatement, handling different types
     */
    private static void setParameter(PreparedStatement stmt, int index, Object value)
            throws SQLException {
        if (value == null) {
            stmt.setNull(index, java.sql.Types.NULL);
        } else if (value instanceof String) {
            stmt.setString(index, (String) value);
        } else if (value instanceof Integer) {
            stmt.setInt(index, (Integer) value);
        } else if (value instanceof Long) {
            stmt.setLong(index, (Long) value);
        } else if (value instanceof Double) {
            stmt.setDouble(index, (Double) value);
        } else if (value instanceof Boolean) {
            stmt.setBoolean(index, (Boolean) value);
        } else {
            // fallback to string representation
            stmt.setString(index, value.toString());
        }
    }

    /**
     * Functional interface for setting parameters in a PreparedStatement
     */
    public interface SqlParameterSetter {
        void setParameters(PreparedStatement stmt) throws SQLException;
    }
}