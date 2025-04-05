package com.example.onlinequizsystem.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Question> questions = new ArrayList<Question>();
        questions.add(new Question("is bao anh in adapted service?","yes"));
        questions.add(new Question("what is bao anh favorite color?","blue"));
        //thats a quiz
        Quiz quiz = new Quiz(1,"general knowledge",Subject.COMPUTER_SCIENCE,questions);

        List<String> userAnswers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Starting Quiz: " + quiz.getTitle());
        for (Question question : quiz.getQuestions()) {
            System.out.println(question.getQuestionText());
            String answer = scanner.nextLine();
            userAnswers.add(answer);
        }
        scanner.close();

        quiz.setGrade(userAnswers);

        System.out.println("final grade: " + quiz.getGrade() + "%");
    }
}
