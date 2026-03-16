//package com.edusync.config;
package com.edusync.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {

    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/edusync_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Pranav@202005"; 

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Error: MySQL JDBC Driver not found.");
        } catch (SQLException e) {
            System.out.println("Error: Could not connect to the database.");
            e.printStackTrace();
        }
        return connection;
    }

    // --- NEW: Added for testing purposes ---
    public static void main(String[] args) {
        System.out.println("Testing Database Connection...");
        Connection conn = getConnection();
        
        if (conn != null) {
            System.out.println("SUCCESS! You are officially connected to MySQL.");
        } else {
            System.out.println("FAILED! Connection could not be established.");
        }
    }
}