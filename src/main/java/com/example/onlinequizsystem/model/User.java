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
}
