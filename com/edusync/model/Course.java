package com.edusync.model;

import java.util.ArrayList;
import java.util.List;

public class Course {


    private int courseId;
    private String courseCode;
    private String courseName;
    private int credits;
    private String department;

    
    private Faculty instructor;
    private List<Student> enrolledStudents;


    public Course(int courseId, String courseCode, String courseName, int credits, String department) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.department = department;
        
      
        this.instructor = null;
        this.enrolledStudents = new ArrayList<>();
    }

    // --- Getters ---
    public int getCourseId() { return courseId; }
    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public int getCredits() { return credits; }
    public String getDepartment() { return department; }
    public Faculty getInstructor() { return instructor; }
    public List<Student> getEnrolledStudents() { return enrolledStudents; }

    // --- Setters ---
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public void setCredits(int credits) { this.credits = credits; }
    public void setDepartment(String department) { this.department = department; }

    // --- Custom Action Methods ---
    public void assignInstructor(Faculty instructor) {
        this.instructor = instructor;
    }

    public void addStudent(Student student) {
        if (!enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
        }
    }

    public void removeStudent(Student student) {
        enrolledStudents.remove(student);
    }

    @Override
    public String toString() {
        String instructorName = (instructor != null) ? instructor.getName() : "TBA";
        return "Course{" +
                "ID=" + courseId +
                ", Code='" + courseCode + '\'' +
                ", Name='" + courseName + '\'' +
                ", Credits=" + credits +
                ", Department='" + department + '\'' +
                ", Instructor=" + instructorName +
                ", Enrolled=" + enrolledStudents.size() +
                '}';
    }
}
