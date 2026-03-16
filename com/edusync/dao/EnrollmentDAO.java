package com.edusync.dao;

import com.edusync.config.DBConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class EnrollmentDAO {

    /**
     * Enrolls a student in a course.
     */
    public boolean enrollStudent(int studentId, int courseId) {
        String query = "INSERT INTO enrollments (student_id, course_id, enrollment_date, status) VALUES (?, ?, ?, 'ACTIVE')";

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            stmt.setDate(3, java.sql.Date.valueOf(LocalDate.now())); // Gets today's date

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error: Student might already be enrolled in this course.");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Drops a student from a course.
     */
    public boolean dropStudent(int studentId, int courseId) {
        String query = "UPDATE enrollments SET status = 'DROPPED' WHERE student_id = ? AND course_id = ?";

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
