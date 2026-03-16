package com.edusync.controllers;

import com.edusync.model.Student;
import com.edusync.model.Course;
import com.edusync.services.StudentService;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentDashboardController {

    @FXML private Label welcomeLabel;
    @FXML private Label rollNumberLabel;
    @FXML private Label departmentLabel;
    @FXML private Label cgpaLabel;
    
    @FXML private TableView<Course> courseTable;
    @FXML private TableColumn<Course, String> colCode;
    @FXML private TableColumn<Course, String> colName;
    @FXML private TableColumn<Course, Integer> colCredits;

    private Student loggedInStudent;
    private StudentService studentService;

    public StudentDashboardController() {
        this.studentService = new StudentService();
    }

    /**
     * This method is called by the LoginController to pass the user data over
     * before the screen actually displays.
     */
    public void setStudentData(Student student) {
        this.loggedInStudent = student;
        populateDashboard();
    }

    private void populateDashboard() {
        // 1. Set all the text labels
        welcomeLabel.setText("Welcome, " + loggedInStudent.getName() + "!");
        rollNumberLabel.setText("Roll No: " + loggedInStudent.getRollNumber());
        departmentLabel.setText("Dept: " + loggedInStudent.getDepartment());
        cgpaLabel.setText(String.valueOf(loggedInStudent.getCgpa()));

        // 2. Set up the table columns so JavaFX knows which Course attributes to display
        colCode.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colCredits.setCellValueFactory(new PropertyValueFactory<>("credits"));

        // 3. (Future Step) Fetch enrolled courses from the database and load them into the table
        // List<Course> myCourses = studentService.getMyCourses(loggedInStudent.getId());
        // courseTable.getItems().setAll(myCourses);
    }
}
