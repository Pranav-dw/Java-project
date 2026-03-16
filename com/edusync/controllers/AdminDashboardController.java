package com.edusync.controllers;

import com.edusync.model.Admin;
import com.edusync.model.Course;
import com.edusync.services.AdminService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class AdminDashboardController {

    @FXML private Label adminNameLabel;
    
    // Table components
    @FXML private TableView<Course> courseTable;
    @FXML private TableColumn<Course, Integer> colId;
    @FXML private TableColumn<Course, String> colCode;
    @FXML private TableColumn<Course, String> colName;
    @FXML private TableColumn<Course, Integer> colCredits;
    @FXML private TableColumn<Course, String> colDept;

    private Admin loggedInAdmin;
    private AdminService adminService;

    public AdminDashboardController() {
        // Initialize the service so we can talk to the database
        this.adminService = new AdminService();
    }

    /**
     * Called when the Admin successfully logs in.
     */
    public void setAdminData(Admin admin) {
        this.loggedInAdmin = admin;
        adminNameLabel.setText("Welcome, " + admin.getName());
        
        // Link the Table columns to the Course.java properties
        colId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colCode.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colCredits.setCellValueFactory(new PropertyValueFactory<>("credits"));
        colDept.setCellValueFactory(new PropertyValueFactory<>("department"));
        
        // Load the courses as soon as the dashboard opens
        handleViewCourses();
    }

    /**
     * Triggered by the "View Course Catalog" button.
     * Fetches courses from the database and displays them in the table.
     */
    @FXML
    public void handleViewCourses() {
        // 1. Ask the service to get all courses from the database
        List<Course> catalog = adminService.getCourseCatalog(); // Note: we need to ensure this method exists in AdminService or CourseService!
        
        // 2. Convert the standard Java List into an ObservableList (which JavaFX requires for tables)
        ObservableList<Course> observableCatalog = FXCollections.observableArrayList(catalog);
        
        // 3. Put the data into the table on the screen
        courseTable.setItems(observableCatalog);
    }
}