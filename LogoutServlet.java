package com.skillconnect.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Get existing session, don't create a new one

        if (session != null) {
            session.invalidate(); // Invalidate the session, removing all attributes
        }

        // Redirect to the login page
        response.sendRedirect("login.jsp");
    }
}
