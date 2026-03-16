package com.edusync.services;

import com.edusync.dao.CourseDAO;
import com.edusync.model.Course;
import java.util.List;

public class CourseService {

    private CourseDAO courseDAO;

    public CourseService() {
        this.courseDAO = new CourseDAO();
    }

    /**
     * Validates and creates a new course.
     */
    public boolean addNewCourse(Course course) {
        if (course.getCourseCode() == null || course.getCourseCode().length() < 3) {
            System.out.println("Validation Error: Course code is too short.");
            return false;
        }
        if (course.getCredits() <= 0 || course.getCredits() > 6) {
            System.out.println("Validation Error: Credits must be between 1 and 6.");
            return false;
        }

        System.out.println("Validation passed. Adding course to catalog...");
        return courseDAO.addCourse(course);
    }

    /**
     * Assigns a professor to a class, ensuring valid IDs.
     */
    public boolean assignProfessor(int courseId, int facultyId) {
        if (courseId <= 0 || facultyId <= 0) {
            System.out.println("Error: Invalid Course or Faculty ID.");
            return false;
        }
        return courseDAO.assignInstructorToCourse(courseId, facultyId);
    }

    /**
     * Fetches the entire catalog.
     */
    public List<Course> getCourseCatalog() {
        return courseDAO.getAllCourses();
    }
}