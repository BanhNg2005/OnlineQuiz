package com.example.onlinequizsystem.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SchemaInitializer {
    public static void initializeSchema() {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            Statement stmt = conn.createStatement();

            // Create tables
            stmt.execute("CREATE TABLE IF NOT EXISTS users ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "username VARCHAR(50) NOT NULL UNIQUE, "
                    + "email VARCHAR(100) NOT NULL UNIQUE, "
                    + "password VARCHAR(255) NOT NULL"
                    + ")");
            System.out.println("Users table created.");

            stmt.execute("CREATE TABLE IF NOT EXISTS quizzes ("
            + "id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "title VARCHAR(100) NOT NULL, "
                    + "subject ENUM('MATH','SCIENCE','HISTORY','GEOGRAPHY','LITERATURE','ART','MUSIC','PHYSICAL_EDUCATION','COMPUTER_SCIENCE','LANGUAGE_ARTS','SOCIAL_STUDIES','ECONOMICS','PSYCHOLOGY','PHILOSOPHY','BUSINESS_STUDIES') NOT NULL, "
                    + "description VARCHAR(255), "
                    + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
                    + "created_by INT, "
                    + "FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE CASCADE"
                    + ")");

            System.out.println("Quizzes table created.");

            stmt.execute("CREATE TABLE IF NOT EXISTS questions ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "question_text VARCHAR(255) NOT NULL, "
                    + "answer VARCHAR(255) NOT NULL"
                    + ")");
            System.out.println("Questions table created.");

            System.out.println("Database schema setup complete!");

        } catch (SQLException e) {
            System.err.println("Error setting up database: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DbUtil.closeQuietly(conn);
        }
    }

    public static void main(String[] args) {
        // Run this method to create the database schema
        initializeSchema();
    }
}
