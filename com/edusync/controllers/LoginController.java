package com.edusync.controllers;

import com.edusync.services.UserService;
import com.edusync.model.User;
import com.edusync.model.Student;
import com.edusync.model.Faculty;
import com.edusync.model.Admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Label errorLabel;

    private UserService userService;

    public LoginController() {
        this.userService = new UserService();
    }

    @FXML
    public void initialize() {
        errorLabel.setText(""); 
    }

    @FXML
    public void handleLoginButton() {
        String email = emailField.getText();
        String password = passwordField.getText();
        errorLabel.setText("");

        User loggedInUser = userService.login(email, password);

        if (loggedInUser != null) {
            errorLabel.setStyle("-fx-text-fill: green;");
            errorLabel.setText("Login Successful! Loading...");
            
            // NEW: Trigger the screen switch!
            loadDashboard(loggedInUser);
            
        } else {
            errorLabel.setStyle("-fx-text-fill: red;");
            errorLabel.setText("Invalid email or password.");
        }
    }

    /**
     * NEW METHOD: Handles the logic of closing the login screen
     * and opening the correct dashboard based on the user's role.
     */
    private void loadDashboard(User user) {
        try {
            String fxmlFile = "";
            
            // 1. Determine which screen to load
            if (user.getRole().equals("STUDENT")) {
                fxmlFile = "/fxml/StudentDashboard.fxml";
            } else if (user.getRole().equals("FACULTY")) {
                fxmlFile = "/fxml/FacultyDashboard.fxml";
            } else if (user.getRole().equals("ADMIN")) {
                fxmlFile = "/fxml/AdminDashboard.fxml";
            }

            // 2. Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // 3. Pass the user data to the new screen's controller
            if (user.getRole().equals("STUDENT")) {
                StudentDashboardController controller = loader.getController();
                controller.setStudentData((Student) user);
            } else if (user.getRole().equals("FACULTY")) {
                FacultyDashboardController controller = loader.getController();
                controller.setFacultyData((Faculty) user);
            } else if (user.getRole().equals("ADMIN")) {
                AdminDashboardController controller = loader.getController();
                controller.setAdminData((Admin) user);
            }

            // 4. Grab the current window (Stage) and swap the Scene!
            Stage currentStage = (Stage) loginButton.getScene().getWindow();
            currentStage.setScene(new Scene(root));
            currentStage.centerOnScreen(); // Keep the window perfectly centered
            currentStage.show();

        } catch (Exception e) {
            System.out.println("Error loading the dashboard screen!");
            e.printStackTrace();
            errorLabel.setText("System error: Could not load dashboard.");
        }
    }
}