package com.edusync.services;

import com.edusync.model.Faculty;
import com.edusync.dao.FacultyDAO;
import com.edusync.dao.GradeDAO;
import com.edusync.dao.AttendanceDAO;


import java.time.LocalDate;

public class FacultyService {

    private FacultyDAO facultyDAO;
    private GradeDAO gradeDAO;
    private AttendanceDAO attendanceDAO;

    public FacultyService() {
        this.facultyDAO = new FacultyDAO();
        this.gradeDAO = new GradeDAO();
        this.attendanceDAO = new AttendanceDAO();
    }

    /**
     * Records a grade for a student, ensuring the math makes sense.
     */
    public boolean gradeStudent(int studentId, int courseId, String assessmentType, double scoreObtained, double maxScore, String feedback) {
        
        // --- Business Logic & Validation ---
        if (maxScore <= 0) {
            System.out.println("Grading Error: Maximum score must be greater than zero.");
            return false;
        }
        if (scoreObtained < 0 || scoreObtained > maxScore) {
            System.out.println("Grading Error: Score obtained (" + scoreObtained + ") is invalid. It must be between 0 and " + maxScore);
            return false;
        }
        if (assessmentType == null || assessmentType.trim().isEmpty()) {
            System.out.println("Grading Error: Assessment type (e.g., 'Midterm') cannot be blank.");
            return false;
        }

        // --- DAO Interaction ---
        System.out.println("Validation passed. Saving grade to database...");
        return gradeDAO.addGrade(studentId, courseId, assessmentType, scoreObtained, maxScore, feedback);
    }

    /**
     * Marks daily attendance, preventing future dating.
     */
    public boolean submitAttendance(int studentId, int courseId, LocalDate date, String status) {
        
        // Business Rule: You cannot take attendance for a day that hasn't happened yet!
        if (date.isAfter(LocalDate.now())) {
            System.out.println("Attendance Error: Cannot mark attendance for a future date.");
            return false;
        }
        
        // Validate status
        if (!status.equalsIgnoreCase("PRESENT") && !status.equalsIgnoreCase("ABSENT") && !status.equalsIgnoreCase("EXCUSED")) {
            System.out.println("Attendance Error: Status must be PRESENT, ABSENT, or EXCUSED.");
            return false;
        }

        return attendanceDAO.markAttendance(studentId, courseId, date, status.toUpperCase());
    }
}