package com.example.onlinequizsystem.model;

import java.util.List;
import java.util.Set;

public class Quiz {
    private int quizId;
    private String title;
    private Subject subjects;
    private List<Question> questions;

    public Quiz(int quizId, String title, Subject subjects, List<Question> questions) {
        this.quizId = quizId;
        this.title = title;
        this.subjects = subjects;
        this.questions = questions;
    }

    public int getQuizId() {
        return quizId;
    }
    public String getTitle() {
        return title;
    }
    public Subject getSubjects() {
        return subjects;
    }
    public List<Question> getQuestions() {
        return questions;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "quizId=" + quizId +
                ", title='" + title + '\'' +
                ", subjects=" + subjects +
                ", questions=" + questions +
                '}';
    }
}
