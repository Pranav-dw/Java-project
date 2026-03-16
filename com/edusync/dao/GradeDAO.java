package com.edusync.dao;

import com.edusync.config.DBConfig;
import com.edusync.model.Grade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GradeDAO {

    /**
     * Adds a new grade for a student in a specific course.
     */
    public boolean addGrade(int studentId, int courseId, String assessmentType, double scoreObtained, double maxScore, String feedback) {
        String query = "INSERT INTO grades (student_id, course_id, assessment_type, score_obtained, max_score, feedback) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            stmt.setString(3, assessmentType);
            stmt.setDouble(4, scoreObtained);
            stmt.setDouble(5, maxScore);
            stmt.setString(6, feedback);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}