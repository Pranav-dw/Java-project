package com.edusync.services;

import com.edusync.dao.AttendanceDAO;
import java.time.LocalDate;

public class AttendanceService {

    private AttendanceDAO attendanceDAO;

    public AttendanceService() {
        this.attendanceDAO = new AttendanceDAO();
    }

    /**
     * Validates and records a student's attendance for a specific day.
     */
    public boolean recordDailyAttendance(int studentId, int courseId, LocalDate date, String status) {
        
        if (date.isAfter(LocalDate.now())) {
            System.out.println("Validation Error: Cannot record attendance for future dates.");
            return false;
        }
        
        String formattedStatus = status.trim().toUpperCase();
        if (!formattedStatus.equals("PRESENT") && 
            !formattedStatus.equals("ABSENT") && 
            !formattedStatus.equals("EXCUSED")) {
            System.out.println("Validation Error: Status must be PRESENT, ABSENT, or EXCUSED.");
            return false;
        }

        return attendanceDAO.markAttendance(studentId, courseId, date, formattedStatus);
    }
}
