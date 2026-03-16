package com.edusync.services;

import com.edusync.dao.CourseDAO;
import com.edusync.dao.FacultyDAO;
import com.edusync.model.Course;
import com.edusync.model.Faculty;

import java.util.List;

public class AdminService {

    private CourseDAO courseDAO;
    private FacultyDAO facultyDAO;

    public AdminService() {
        this.courseDAO = new CourseDAO();
        this.facultyDAO = new FacultyDAO();
    }

    /**
     * Creates a new course after validating the course code format.
     */
    public boolean createNewCourse(Course course) {
        // Business Rule: Course code must follow a specific pattern (e.g., at least 3 characters)
        if (course.getCourseCode() == null || course.getCourseCode().length() < 3) {
            System.out.println("Course Creation Failed: Invalid Course Code format.");
            return false;
        }
        
        // Business Rule: Credits must be a positive number
        if (course.getCredits() <= 0 || course.getCredits() > 6) {
            System.out.println("Course Creation Failed: Credits must be between 1 and 6.");
            return false;
        }

        return courseDAO.addCourse(course);
    }

    public boolean assignProfessorToClass(int courseId, int facultyId) {
        if (courseId <= 0 || facultyId <= 0) {
            System.out.println("Assignment Failed: Invalid ID provided.");
            return false;
        }
        
        System.out.println("Assigning Faculty ID: " + facultyId + " to Course ID: " + courseId);
        return courseDAO.assignInstructorToCourse(courseId, facultyId);
    }
    
    /**
     * Fetches the entire course catalog to display on the Admin dashboard.
     */
    public List<Course> getCourseCatalog() {
        return courseDAO.getAllCourses();
    }

    /**
     * Prints the course catalog to the console (for admin debugging).
     */
    public void viewCourseCatalog() {
        List<Course> catalog = courseDAO.getAllCourses();
        
        System.out.println("\n--- OFFICIAL COURSE CATALOG ---");
        if (catalog.isEmpty()) {
            System.out.println("The catalog is currently empty.");
        } else {
            for (Course c : catalog) {
                System.out.println(c.getCourseCode() + " - " + c.getCourseName() + " (" + c.getCredits() + " Credits)");
            }
        }
        System.out.println("-------------------------------\n");
    }
}
