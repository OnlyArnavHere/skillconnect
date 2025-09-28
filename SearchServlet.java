package com.skillconnect.controller;

import com.skillconnect.dao.SkillDao;
import com.skillconnect.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private SkillDao skillDao;

    @Override
    public void init() {
        skillDao = new SkillDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String skillQuery = request.getParameter("skill");
        List<User> searchResults = skillDao.searchUsersBySkill(skillQuery);

        // Store the results in the request scope to pass to the JSP
        request.setAttribute("results", searchResults);
        request.setAttribute("query", skillQuery);

        // Forward the request to a JSP to display the results
        RequestDispatcher dispatcher = request.getRequestDispatcher("searchResults.jsp");
        dispatcher.forward(request, response);
    }
}
