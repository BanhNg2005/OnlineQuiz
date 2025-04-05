package com.example.onlinequizsystem.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/OnlineQuiz")
public class OnlineQuizServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        if(username==null || password==null || email==null){

        }
    }
}
