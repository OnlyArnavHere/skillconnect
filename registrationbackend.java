import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String name = request.getParameter("fullname");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");
    String role = request.getParameter("role");
    String location = request.getParameter("location");
    String skills = request.getParameter("skills");
    String password = request.getParameter("password");

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/skillconnect", "root", "yourpassword");

      // Check if email already exists
      PreparedStatement check = conn.prepareStatement("SELECT * FROM users WHERE email = ?");
      check.setString(1, email);
      ResultSet rs = check.executeQuery();
      if (rs.next()) {
        response.sendRedirect("registration.html?error=exists");
        return;
      }

      // Insert new user
      PreparedStatement stmt = conn.prepareStatement(
        "INSERT INTO users (name, email, phone, role, location, skills, password) VALUES (?, ?, ?, ?, ?, ?, ?)"
      );
      stmt.setString(1, name);
      stmt.setString(2, email);
      stmt.setString(3, phone);
      stmt.setString(4, role);
      stmt.setString(5, location);
      stmt.setString(6, skills);
      stmt.setString(7, password);
      stmt.executeUpdate();

      response.sendRedirect("login.html");
    } catch (Exception e) {
      response.getWriter().println("Registration Error: " + e.getMessage());
    }
  }
}
