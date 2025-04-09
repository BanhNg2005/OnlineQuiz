package com.example.onlinequizsystem.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbTestMain {
    public static void main(String[] args) {
        SchemaInitializer.initializeSchema();
        insertUser();
        boolean userExists = verifyTestUser();
        if (userExists) {
            System.out.println("Test user exists in the database.");
        } else {
            System.out.println("Test user does not exist in the database.");
        }
    }

    private static void insertUser() {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO users (username, email, password) VALUES (?, ?, ?)")) {

            stmt.setString(1, "testuser");
            stmt.setString(2, "test@example.com");
            stmt.setString(3, "password123");

            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " user(s) inserted.");

        } catch (SQLException e) {
            if (e.getMessage().contains("unique constraint") || e.getMessage().contains("Unique index")) {
                System.out.println("Test user already exists in the database.");
            } else {
                System.err.println("Error inserting test user: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static boolean verifyTestUser() {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM users WHERE username = ?")) {

            stmt.setString(1, "testuser");

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Test user verified:");
                    System.out.println("ID: " + rs.getInt("id"));
                    System.out.println("Username: " + rs.getString("username"));
                    System.out.println("Email: " + rs.getString("email"));
                    return true;
                } else {
                    System.out.println("Test user not found.");
                    return false;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error verifying test user: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
