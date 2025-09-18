import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/skillconnect", "root", "yourpassword");

      PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
      stmt.setString(1, email);
      stmt.setString(2, password);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        HttpSession session = request.getSession();
        session.setAttribute("userEmail", email);
        session.setAttribute("userName", rs.getString("name"));
        response.sendRedirect("dashboard.html");
      } else {
        response.sendRedirect("login.html?error=invalid");
      }
    } catch (Exception e) {
      response.getWriter().println("Login Error: " + e.getMessage());
    }
  }
}
