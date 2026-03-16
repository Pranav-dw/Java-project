package com.edusync.services;


import com.edusync.dao.UserDAO;
import com.edusync.model.User;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }


    public User login(String email, String password) {
        
        // --- Business Logic & Validation ---
        if (email == null || email.trim().isEmpty()) {
            System.out.println("Error: Email cannot be empty.");
            return null;
        }
        if (!email.contains("@")) {
            System.out.println("Error: Please enter a valid email address.");
            return null;
        }
        if (password == null || password.trim().isEmpty()) {
            System.out.println("Error: Password cannot be empty.");
            return null;
        }

        // --- DAO Interaction ---
        // If it passes all our rules, ask the database if the credentials match
        User loggedInUser = userDAO.authenticate(email, password);

        if (loggedInUser != null) {
            System.out.println("Success! Logged in as: " + loggedInUser.getRole());
            // This calls the accessDashboard() method we built way back in the models!
            loggedInUser.accessDashboard(); 
        } else {
            System.out.println("Error: Invalid email or password combination.");
        }

        return loggedInUser;
    }
}