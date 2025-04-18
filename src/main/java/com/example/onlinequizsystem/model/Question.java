package com.example.onlinequizsystem.model;

public class Question {
    private String questionText;
    private String answer;

    public Question(String questionText, String answer) {
        this.questionText = questionText;
        this.answer = answer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isCorrect(String userAnswer) {
        return answer.equals(userAnswer);
    }
}