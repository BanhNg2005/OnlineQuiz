package com.example.onlinequizsystem.controller;

import com.example.onlinequizsystem.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import java.io.IOException;

@WebServlet("/signUp")
public class signUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //check if user field is missing
        if(email.isEmpty() || username.isEmpty() || password.isEmpty()){
            request.setAttribute("error", "Please fill all the fields");
            request.getRequestDispatcher("signup.jsp").forward(request,response);
        }
        //created a temporary user to validate input
        User tempUser = new User(email,username,password,null,null);

        if (!tempUser.isValidEmail()){
            request.setAttribute("error", "Invalid email");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }
        if (!tempUser.isValidPassword()){
            request.setAttribute("error", "Invalid password");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }
        response.sendRedirect("index.jsp");
        request.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get parameters
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate parameters
        if(email == null || username == null || password == null ||
                email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "Please fill all the fields");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        User tempUser = new User(email, username, password, null, null);

        if (!tempUser.isValidEmail()){
            request.setAttribute("error", "Invalid email");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }
//        if (!tempUser.isUniqueUsername()){
//            request.setAttribute("error", "Invalid email");
//            request.getRequestDispatcher("signup.jsp").forward(request, response);
//            return;
//        }
        if (!tempUser.isValidPassword()){
            request.setAttribute("error", "Invalid password");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }
        response.sendRedirect("index.jsp");
    }
    // hash password
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}