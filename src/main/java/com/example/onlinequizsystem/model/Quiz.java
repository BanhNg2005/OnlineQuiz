package com.example.onlinequizsystem.model;

import java.util.List;
import java.util.Set;
import java.util.Scanner;


public class Quiz {
    private int quizId;
    private String title;
    private Subject subjects;
    private List<Question> questions;
    private double grade;

    public Quiz(int quizId, String title, Subject subjects, List<Question> questions) {
        this.quizId = quizId;
        this.title = title;
        this.subjects = subjects;
        this.questions = questions;
        this.grade = 0;
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
    public double getGrade() { return grade;}

    public void setGrade(List<String> userAnswers){
        if (questions.isEmpty()) {
            System.out.println("No questions available.");
            return;
        }

        int score = 0;
        Scanner scn = new Scanner(System.in);
        for (int i = 0; i < questions.size(); i++) {
            if(questions.get(i).isCorrect(userAnswers.get(i))){
                score++;
            }
        }
        int totalQuestions = questions.size();
        grade = (score*100)/questions.size();

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
