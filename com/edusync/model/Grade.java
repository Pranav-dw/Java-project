package com.edusync.model;

public class Grade {
  
    private int gradeId;
    
 
    private Student student;
    private Course course;
    

    private String assessmentType; // e.g., "Midterm", "Final Exam", "Assignment 1"
    private double scoreObtained;
    private double maxScore;
    private String feedback; // Optional comments from the professor

    public Grade(int gradeId, Student student, Course course, String assessmentType, double scoreObtained, double maxScore, String feedback) {
        this.gradeId = gradeId;
        this.student = student;
        this.course = course;
        this.assessmentType = assessmentType;
        this.scoreObtained = scoreObtained;
        this.maxScore = maxScore;
        this.feedback = feedback;
    }

    // --- Getters ---
    public int getGradeId() { return gradeId; }
    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public String getAssessmentType() { return assessmentType; }
    public double getScoreObtained() { return scoreObtained; }
    public double getMaxScore() { return maxScore; }
    public String getFeedback() { return feedback; }

    // --- Setters ---
    public void setScoreObtained(double scoreObtained) { this.scoreObtained = scoreObtained; }
    public void setFeedback(String feedback) { this.feedback = feedback; }

    // --- Custom Action Method for Analytics ---
    public double calculatePercentage() {
        if (maxScore == 0) return 0.0; // Prevent division by zero errors!
        return (scoreObtained / maxScore) * 100;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "Student=" + student.getName() +
                ", Course=" + course.getCourseCode() +
                ", Type='" + assessmentType + '\'' +
                ", Score=" + scoreObtained + "/" + maxScore +
                " (" + String.format("%.2f", calculatePercentage()) + "%)" +
                '}';
    }
}
    

