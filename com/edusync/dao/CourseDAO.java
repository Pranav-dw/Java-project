package com.edusync.dao;

import com.edusync.config.DBConfig;
import com.edusync.model.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    /**
     * Adds a new course to the database.
     */
    public boolean addCourse(Course course) {
        String query = "INSERT INTO courses (course_code, course_name, credits, department) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, course.getCourseCode());
            stmt.setString(2, course.getCourseName());
            stmt.setInt(3, course.getCredits());
            stmt.setString(4, course.getDepartment());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves EVERY course from the database and returns them as a Java List.
     */
    public List<Course> getAllCourses() {
        List<Course> courseList = new ArrayList<>();
        String query = "SELECT * FROM courses";

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            // Loop through every single row the database returned
            while (rs.next()) {
                // Create a new Course object for the current row
                Course course = new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_code"),
                        rs.getString("course_name"),
                        rs.getInt("credits"),
                        rs.getString("department")
                );
                
                // Add it to our list
                courseList.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return courseList; // Hand the full list back to the UI or Service layer
    }

    /**
     * Updates a course to assign a specific Faculty member to teach it.
     */
    public boolean assignInstructorToCourse(int courseId, int facultyId) {
        // We update the foreign key column 'instructor_id'
        String query = "UPDATE courses SET instructor_id = ? WHERE course_id = ?";

        try (Connection conn = DBConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, facultyId);
            stmt.setInt(2, courseId);

            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected > 0 ? "Instructor assigned!" : "Course not found.");
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}