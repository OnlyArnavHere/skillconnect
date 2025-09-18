@WebServlet("/login")
public class LoginServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    // Dummy validation (replace with DB check)
    if ("user@example.com".equals(email) && "1234".equals(password)) {
      response.sendRedirect("dashboard.html");
    } else {
      response.sendRedirect("login.html?error=invalid");
    }
  }
}
