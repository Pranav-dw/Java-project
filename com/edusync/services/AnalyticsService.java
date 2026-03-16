package com.edusync.services;

import com.edusync.dao.GradeDAO;
import com.edusync.dao.AttendanceDAO;

public class AnalyticsService {

    private GradeDAO gradeDAO;
    private AttendanceDAO attendanceDAO;

    public AnalyticsService() {
        this.gradeDAO = new GradeDAO();
        this.attendanceDAO = new AttendanceDAO();
    }

    /**
     * Calculates the estimated CGPA based on all current grades.
     * (Note: In a full implementation, you would add a getGradesByStudent() 
     * method to your GradeDAO to fetch the list of scores for this math).
     */
    public double calculateCurrentCGPA(int studentId) {
        System.out.println("Analyzing grade data for Student ID: " + studentId + "...");
        
        // Placeholder for the actual math logic:
        // 1. Fetch List<Grade> from GradeDAO
        // 2. Loop through the list, summing (scoreObtained / maxScore)
        // 3. Weight by course credits
        // 4. Return the final CGPA out of 10.0
        
        double estimatedCgpa = 8.5; // Example calculation result
        System.out.println("Current Estimated CGPA: " + estimatedCgpa);
        return estimatedCgpa;
    }

    /**
     * Checks if a student is at risk of failing due to poor attendance.
     * This is the "Smart Alert" feature for your Admin/Faculty dashboard!
     */
    public boolean checkAttendanceWarning(int studentId, int courseId) {
        System.out.println("Scanning attendance records for Student ID: " + studentId + " in Course: " + courseId + "...");
        
        // Placeholder for the actual logic:
        // 1. Fetch List<Attendance> from AttendanceDAO
        // 2. Count how many times status == "ABSENT"
        // 3. If absences > 5, trigger a warning!
        
        int totalAbsences = 6; // Example data from DAO
        
        if (totalAbsences >= 5) {
            System.out.println("⚠️ SMART ALERT: Student " + studentId + " has missed " + totalAbsences + " classes. Academic warning required!");
            return true; 
        } else {
            System.out.println("Attendance is within acceptable limits.");
            return false;
        }
    }
    
    /**
     * Generates a complete performance overview for a student.
     */
    public void generateStudentPerformanceReport(int studentId) {
        System.out.println("\n=== SMART PERFORMANCE REPORT ===");
        System.out.println("Student ID: " + studentId);
        System.out.println("--------------------------------");
        
        calculateCurrentCGPA(studentId);
        
        // Check attendance across a hypothetical course (e.g., Course ID 101)
        checkAttendanceWarning(studentId, 101); 
        
        System.out.println("================================\n");
    }
}