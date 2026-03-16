package com.edusync.model;

import java.util.ArrayList;
import java.util.List;

public class Faculty extends User {

    
    private String employeeId;
    private String department;
    private String designation;
    private String subject; 
 
    private List<Course> coursesTaught;

    public Faculty(int id, String name, String email, String password, String employeeId, String department, String designation, String subject) {
        super(id, name, email, password, "FACULTY");
        this.employeeId = employeeId;
        this.department = department;
        this.designation = designation;
        this.subject = subject;
        
        this.coursesTaught = new ArrayList<>(); 
    }

    // --- Getters ---
    public String getEmployeeId() { return employeeId; }
    public String getDepartment() { return department; }
    public String getDesignation() { return designation; }
    public String getSubject() { return subject; }
    public List<Course> getCoursesTaught() { return coursesTaught; }

    // --- Setters ---
    public void setDepartment(String department) { this.department = department; }
    public void setDesignation(String designation) { this.designation = designation; }
    public void setSubject(String subject) { this.subject = subject; }

    // --- Custom Action Methods ---
    public void assignCourse(Course course) {
        if (!coursesTaught.contains(course)) {
            coursesTaught.add(course);
            System.out.println("Professor " + getName() + " assigned to teach " + course.getCourseName());
        }
    }

    public void removeCourse(Course course) {
        coursesTaught.remove(course);
    }

    @Override
    public void accessDashboard() {
        System.out.println("=== FACULTY PORTAL ===");
        System.out.println("Welcome, " + designation + " " + getName());
        System.out.println("Department: " + department + " | Expertise: " + subject);
        System.out.println("Active Classes: " + coursesTaught.size());
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "Name='" + getName() + '\'' +
                ", EmployeeId='" + employeeId + '\'' +
                ", Department='" + department + '\'' +
                ", Designation='" + designation + '\'' +
                ", Subject='" + subject + '\'' +
                ", AssignedCourses=" + coursesTaught.size() +
                '}';
    }
}
