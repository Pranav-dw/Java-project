package com.edusync.model;


import java.util.ArrayList;
import java.util.List;

public class Student extends User {

    private String rollNumber;
    private String department;
    private int semester;
    private double cgpa;

    private List<Course> enrolledCourses;

    public Student(int id, String name, String email, String password, String rollNumber, String department, int semester) {
        super(id, name, email, password, "STUDENT");
        this.rollNumber = rollNumber;
        this.department = department;
        this.semester = semester;
        this.cgpa = 0.0; 
        this.enrolledCourses = new ArrayList<>(); 
    }

    // --- Getters ---
    public String getRollNumber() { return rollNumber; }
    public String getDepartment() { return department; }
    public int getSemester() { return semester; }
    public double getCgpa() { return cgpa; }
    public List<Course> getEnrolledCourses() { return enrolledCourses; }

    // --- Setters ---
    public void setDepartment(String department) { this.department = department; }
    public void setSemester(int semester) { this.semester = semester; }
    public void setCgpa(double cgpa) { this.cgpa = cgpa; }

    // --- Custom Methods ---
    public void addCourse(Course course) {
        this.enrolledCourses.add(course);
        System.out.println(getName() + " enrolled in " + course.getCourseName());
    }

    @Override
    public void accessDashboard() {
        System.out.println("Welcome to Student Dashboard, " + getName());
        System.out.println("Current Semester: " + semester + " | CGPA: " + cgpa);
    }

    @Override
    public String toString() {
        return "Student{" +
                "Name='" + getName() + '\'' +
                ", RollNumber='" + rollNumber + '\'' +
                ", Department='" + department + '\'' +
                ", Semester=" + semester +
                ", CGPA=" + cgpa +
                ", EnrolledCoursesCount=" + enrolledCourses.size() +
                '}';
    }
}