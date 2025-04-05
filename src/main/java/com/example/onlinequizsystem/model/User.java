package com.example.onlinequizsystem.model;

import java.util.List;

public class User {
    private String email;
    private String username;
    private String password;
    private List<Quiz> createdQuizzes;
    private List<Quiz> attemptedQuizzes;

    // Constructor
    public User(String email, String username, String password, List<Quiz> createdQuizzes, List<Quiz> attemptedQuizzes) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.createdQuizzes = createdQuizzes;
        this.attemptedQuizzes = attemptedQuizzes;
    }

    // Getters and methods
    public String getEmail() {
        return email;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public List<Quiz> getCreatedQuizzes() {
        return createdQuizzes;
    }
    public List<Quiz> getAttemptedQuizzes() {
        return attemptedQuizzes;
    }

    // validate email
    public boolean isValidEmail() {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    // validate username
    public boolean isValidPassword() {
        // at least 8 characters and a number
        String passwordRegex = "^(?=.*[0-9]).{8,}$";
        return password.matches(passwordRegex);
    }

    // toString method
    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createdQuizzes=" + createdQuizzes +
                ", attemptedQuizzes=" + attemptedQuizzes +
                '}';
    }

}
