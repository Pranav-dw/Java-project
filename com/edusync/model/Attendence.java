package com.edusync.model;
import java.time.LocalDate;

public class Attendence {


    private int attendanceId;
    
    private Student student;
    private Course course;
    private LocalDate date;
    private String status; // e.g., "PRESENT", "ABSENT", "EXCUSED"

    // Constructor
    public Attendence(int attendanceId, Student student, Course course, LocalDate date, String status) {
        this.attendanceId = attendanceId;
        this.student = student;
        this.course = course;
        this.date = date;
        this.status = status;
    }

    // --- Getters ---
    public int getAttendanceId() { return attendanceId; }
    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public LocalDate getDate() { return date; }
    public String getStatus() { return status; }

    // --- Setters ---
    public void setStatus(String status) { this.status = status; }
    // Note: We don't usually set new dates, students, or courses for an existing attendance record.
    // If a mistake is made, it's safer to delete the record and create a new one, or just update the status.

    @Override
    public String toString() {
        return "Attendance{" +
                "ID=" + attendanceId +
                ", Date=" + date +
                ", Student=" + student.getName() +
                ", Course=" + course.getCourseCode() +
                ", Status='" + status + '\'' +
                '}';
    }
}

