package com.edusync.dao;

import com.edusync.config.DBConfig;
import com.edusync.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public boolean addStudent(Student student) {
        String insertUserQuery = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, 'STUDENT')";
        String insertStudentQuery = "INSERT INTO students (user_id, roll_number, department, semester, cgpa) VALUES (?, ?, ?, ?, ?)";

        Connection conn = null;
        try {
            conn = DBConfig.getConnection();
            // Turn off auto-commit so we can do a "Transaction" (all or nothing)
            conn.setAutoCommit(false); 

            // 1. Insert into the users table first, and ask MySQL for the generated ID back
            PreparedStatement userStmt = conn.prepareStatement(insertUserQuery, Statement.RETURN_GENERATED_KEYS);
            userStmt.setString(1, student.getName());
            userStmt.setString(2, student.getEmail());
            userStmt.setString(3, student.getPassword());
            userStmt.executeUpdate();

            // Retrieve the new User ID that MySQL just created
            ResultSet generatedKeys = userStmt.getGeneratedKeys();
            int newUserId = 0;
            if (generatedKeys.next()) {
                newUserId = generatedKeys.getInt(1);
            }

            // 2. Now use that new ID to insert the rest of the data into the students table
            PreparedStatement studentStmt = conn.prepareStatement(insertStudentQuery);
            studentStmt.setInt(1, newUserId);
            studentStmt.setString(2, student.getRollNumber());
            studentStmt.setString(3, student.getDepartment());
            studentStmt.setInt(4, student.getSemester());
            studentStmt.setDouble(5, student.getCgpa());
            studentStmt.executeUpdate();

            // Commit the transaction (save both tables permanently)
            conn.commit();
            System.out.println("Student added successfully to database!");
            return true;

        } catch (SQLException e) {
            // If anything fails, rollback the whole process so we don't end up with half a student
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            e.printStackTrace();
            return false;
        } finally {
            // Always restore default behavior and close connection
            if (conn != null) {
                try { conn.setAutoCommit(true); conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }

    /**
     * Retrieves a student by their ID using an SQL JOIN.
     */
    public Student getStudentById(int userId) {
        // This JOIN connects the two tables where the IDs match
        String query = "SELECT u.id, u.name, u.email, u.password, s.roll_number, s.department, s.semester, s.cgpa " +
                       "FROM users u INNER JOIN students s ON u.id = s.user_id " +
                       "WHERE u.id = ?";

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Rebuild the Java object using the combined data from both tables
                return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("roll_number"),
                        rs.getString("department"),
                        rs.getInt("semester")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}