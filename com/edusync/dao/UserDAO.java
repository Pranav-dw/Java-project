package com.edusync.dao;

import com.edusync.config.DBConfig;
import com.edusync.model.User; // Assuming you have a base User class that Student, Faculty, and Admin extend
import com.edusync.model.Student;
import com.edusync.model.Faculty;
import com.edusync.model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    /**
     * Authenticates a user based on email and password.
     * Returns a User object (Student, Faculty, or Admin) if successful, or null if it fails.
     */
    public User authenticate(String email, String password) {
        
        // The ? are placeholders to prevent SQL Injection attacks
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        
        // Try-with-resources ensures the connection closes automatically
        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            // Fill in the ? placeholders with the actual email and password
            stmt.setString(1, email);
            stmt.setString(2, password);
            
            // Execute the query and get the results
            ResultSet rs = stmt.executeQuery();
            
            // If a row is found, the credentials are correct!
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String role = rs.getString("role");
                
                // Polymorphism: We create the specific child class based on their role
                if (role.equals("STUDENT")) {
                    // In a full app, you would do a JOIN here to get roll_number, etc.
                    // For now, we instantiate it with basic data to get them logged in.
                    return new Student(id, name, email, password, "TBD", "TBD", 1);
                } 
                else if (role.equals("FACULTY")) {
                    return new Faculty(id, name, email, password, "TBD", "TBD", "TBD", "TBD");
                } 
                else if (role.equals("ADMIN")) {
                    return new Admin(id, name, email, password, "Main Office", 5);
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Database error during authentication!");
            e.printStackTrace();
        }
        
        // If no user is found, or an error occurs, return null
        return null;
    }
}

