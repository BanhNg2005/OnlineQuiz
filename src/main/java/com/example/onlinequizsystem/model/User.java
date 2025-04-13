package com.example.onlinequizsystem.model;

import java.util.List;

public class User {
    private String email;
    private String username;
    private String password;
    private List<Quiz> createdQuizzes;

    // Constructor
    public User(String email, String username, String password, List<Quiz> createdQuizzes) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.createdQuizzes = createdQuizzes;
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


    // validate email
    public boolean isValidEmail() {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    // validate password
    public boolean isValidPassword() {
        // at least 8 characters and a number
        String passwordRegex = "^(?=.*[0-9]).{8,}$";
        return password.matches(passwordRegex);
    }

    // check if username is unique and not already taken
    public boolean isUniqueUsername(List<User> users) {
        for (User user : users) {
            if (user.getUsername().equals(this.username)) {
                System.out.println("Username already taken. Please choose a different one!");
                return false;
            }
        }
        return true;
    }

    // toString method
    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createdQuizzes=" + createdQuizzes +
                '}';
    }

}
