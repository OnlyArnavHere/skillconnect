package com.skillconnect.controller;

import com.skillconnect.dao.UserDao;
import com.skillconnect.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() {
        userDao = new UserDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("register".equals(action)) {
            handleRegistration(request, response);
        } else if ("login".equals(action)) {
            handleLogin(request, response);
        } else {
            // Handle unknown action
            response.sendRedirect("index.html");
        }
    }

    private void handleRegistration(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User newUser = new User();
        newUser.setFirstName(request.getParameter("firstName"));
        newUser.setLastName(request.getParameter("lastName"));
        newUser.setEmail(request.getParameter("email"));
        newUser.setPassword(request.getParameter("password"));

        boolean success = userDao.registerUser(newUser);

        if (success) {
            response.sendRedirect("login.html?registration=success");
        } else {
            response.sendRedirect("registration.html?error=1");
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userDao.loginUser(email, password);

        if (user != null) {
            // Create a session and store user info
            HttpSession session = request.getSession();
            session.setAttribute("authUser", user); // Store the entire User object
            session.setAttribute("username", user.getFirstName());
            response.sendRedirect("dashboard.html");
        } else {
            response.sendRedirect("login.html?error=1");
        }
    }
}
