package com.edusync.services;


import com.edusync.dao.StudentDAO;
import com.edusync.dao.EnrollmentDAO;
import com.edusync.model.Student;

public class StudentService {

    private StudentDAO studentDAO;
    private EnrollmentDAO enrollmentDAO;

    public StudentService() {
        this.studentDAO = new StudentDAO();
        this.enrollmentDAO = new EnrollmentDAO();
    }

    /**
     * Registers a new student, enforcing college data rules.
     */
    public boolean registerNewStudent(Student student) {
        
        // Business Rule: CGPA must be a valid number when starting
        if (student.getCgpa() < 0.0 || student.getCgpa() > 10.0) {
            System.out.println("Registration Failed: Invalid CGPA format.");
            return false;
        }
        
        // Business Rule: Roll number cannot be empty
        if (student.getRollNumber() == null || student.getRollNumber().isEmpty()) {
            System.out.println("Registration Failed: Roll number is required.");
            return false;
        }

        // Pass to DAO to save to database
        return studentDAO.addStudent(student);
    }

    /**
     * Allows a student to register for a class.
     */
    public boolean enrollInCourse(int studentId, int courseId) {
        // Here you could add complex logic, like:
        // "Check if the student already has 5 classes. If yes, reject enrollment."
        // "Check if the student has unpaid fees before allowing enrollment."
        
        System.out.println("Processing course enrollment request...");
        return enrollmentDAO.enrollStudent(studentId, courseId);
    }
    
    /**
     * Fetches a complete student profile.
     */
    public Student getStudentProfile(int userId) {
        if (userId <= 0) {
            System.out.println("Error: Invalid User ID.");
            return null;
        }
        return studentDAO.getStudentById(userId);
    }
}
