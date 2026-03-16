package com.edusync.model;

public class Admin extends User {

    private String department;
    private int clearanceLevel;

    public Admin(int userId, String name, String email, String password, String department, int clearanceLevel) {
        super(userId, name, email, password, "ADMIN");

        this.department = department;
        this.clearanceLevel = clearanceLevel;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getClearanceLevel() {
        return clearanceLevel;
    }

    public void setClearanceLevel(int clearanceLevel) {
        this.clearanceLevel = clearanceLevel;
    }

    @Override
    public void accessDashboard() {
        System.out.println("=== ADMIN CONTROL PANEL ===");
        System.out.println("Welcome, Administrator " + getName());
        System.out.println("Department: " + this.department + " | Clearance: Level " + this.clearanceLevel);
        System.out.println("Options: 1. Manage Users | 2. System Settings | 3. Generate Reports");
    }
}
