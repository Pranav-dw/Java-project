package com.edusync.controllers;

import com.edusync.model.Faculty;
import com.edusync.model.Course;
import com.edusync.services.FacultyService;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FacultyDashboardController {

    @FXML private Label welcomeLabel;
    @FXML private Label deptLabel;
    @FXML private Label subjectLabel;
    
    // Table Components
    @FXML private TableView<Course> courseTable;
    @FXML private TableColumn<Course, String> colCode;
    @FXML private TableColumn<Course, String> colName;
    @FXML private TableColumn<Course, Integer> colCredits;

    private Faculty loggedInFaculty;
    private FacultyService facultyService;

    public FacultyDashboardController() {
        this.facultyService = new FacultyService();
    }

    /**
     * Initializes the dashboard with the specific professor's data.
     */
    public void setFacultyData(Faculty faculty) {
        this.loggedInFaculty = faculty;
        
        // Populate Sidebar Labels
        welcomeLabel.setText("Welcome, Prof. " + faculty.getName());
        deptLabel.setText("Dept: " + faculty.getDepartment());
        subjectLabel.setText("Subject: " + faculty.getSubject());
        
        // Setup Table Columns to map directly to Course properties
        colCode.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colCredits.setCellValueFactory(new PropertyValueFactory<>("credits"));
        
        // In the future, you would add a method like getCoursesByInstructor(facultyId)
        // to your CourseDAO, call it here, and push the list to the table!
        // List<Course> assignedCourses = courseService.getAssignedCourses(loggedInFaculty.getId());
        // courseTable.getItems().setAll(assignedCourses);
    }
}