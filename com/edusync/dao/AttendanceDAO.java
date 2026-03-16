package com.edusync.dao;

import com.edusync.config.DBConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class AttendanceDAO {

    /**
     * Marks attendance for a specific student on a specific day.
     */
    public boolean markAttendance(int studentId, int courseId, LocalDate date, String status) {
        String query = "INSERT INTO attendance (student_id, course_id, attendance_date, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            stmt.setDate(3, java.sql.Date.valueOf(date));
            stmt.setString(4, status); // e.g., "PRESENT", "ABSENT"

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
