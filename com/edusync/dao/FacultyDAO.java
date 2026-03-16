package com.edusync.dao;

import com.edusync.config.DBConfig;
import com.edusync.model.Faculty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacultyDAO {

    /**
     * Adds a new faculty member to BOTH the users table and the faculty table.
     */
    public boolean addFaculty(Faculty faculty) {
        String insertUserQuery = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, 'FACULTY')";
        String insertFacultyQuery = "INSERT INTO faculty (user_id, employee_id, department, designation, subject) VALUES (?, ?, ?, ?, ?)";

        Connection conn = null;
        try {
            conn = DBConfig.getConnection();
            // Start transaction
            conn.setAutoCommit(false); 

            // 1. Insert into the users table and get the auto-generated ID
            PreparedStatement userStmt = conn.prepareStatement(insertUserQuery, Statement.RETURN_GENERATED_KEYS);
            userStmt.setString(1, faculty.getName());
            userStmt.setString(2, faculty.getEmail());
            userStmt.setString(3, faculty.getPassword());
            userStmt.executeUpdate();

            ResultSet generatedKeys = userStmt.getGeneratedKeys();
            int newUserId = 0;
            if (generatedKeys.next()) {
                newUserId = generatedKeys.getInt(1);
            }

            // 2. Insert into the faculty table using the new user_id
            PreparedStatement facultyStmt = conn.prepareStatement(insertFacultyQuery);
            facultyStmt.setInt(1, newUserId);
            facultyStmt.setString(2, faculty.getEmployeeId());
            facultyStmt.setString(3, faculty.getDepartment());
            facultyStmt.setString(4, faculty.getDesignation());
            facultyStmt.setString(5, faculty.getSubject());
            facultyStmt.executeUpdate();

            // Save both permanently
            conn.commit();
            System.out.println("Faculty member added successfully!");
            return true;

        } catch (SQLException e) {
            // Rollback if anything goes wrong
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try { conn.setAutoCommit(true); conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }

    /**
     * Retrieves a faculty member by their ID using an SQL JOIN.
     */
    public Faculty getFacultyById(int userId) {
        String query = "SELECT u.id, u.name, u.email, u.password, f.employee_id, f.department, f.designation, f.subject " +
                       "FROM users u INNER JOIN faculty f ON u.id = f.user_id " +
                       "WHERE u.id = ?";

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Reconstruct the Faculty object
                return new Faculty(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("employee_id"),
                        rs.getString("department"),
                        rs.getString("designation"),
                        rs.getString("subject")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves a list of all faculty members (useful for Admins).
     */
    public List<Faculty> getAllFaculty() {
        List<Faculty> facultyList = new ArrayList<>();
        String query = "SELECT u.id, u.name, u.email, u.password, f.employee_id, f.department, f.designation, f.subject " +
                       "FROM users u INNER JOIN faculty f ON u.id = f.user_id";

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Faculty faculty = new Faculty(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("employee_id"),
                        rs.getString("department"),
                        rs.getString("designation"),
                        rs.getString("subject")
                );
                facultyList.add(faculty);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facultyList;
    }
}
