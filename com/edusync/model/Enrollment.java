package com.edusync.model;

import java.time.LocalDate;

public class Enrollment {

    private int enrollmentId;
    
    // The Bridge: Connects exactly one Student to exactly one Course
    private Student student;
    private Course course;
    
    // Additional tracking data
    private LocalDate enrollmentDate;
    private String status; // e.g., "ACTIVE", "DROPPED", "COMPLETED"

    public Enrollment(int enrollmentId, Student student, Course course, LocalDate enrollmentDate, String status) {
        this.enrollmentId = enrollmentId;
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }

    // --- Getters ---
    public int getEnrollmentId() { return enrollmentId; }
    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public String getStatus() { return status; }

    // --- Setters ---
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Enrollment{" +
                "ID=" + enrollmentId +
                ", Student=" + student.getName() +
                ", Course=" + course.getCourseCode() +
                ", Date=" + enrollmentDate +
                ", Status='" + status + '\'' +
                '}';
    }
}