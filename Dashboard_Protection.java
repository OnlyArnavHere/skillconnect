@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html"); // Not logged in → redirect
        } else {
            // Show dashboard content
            RequestDispatcher rd = request.getRequestDispatcher("dashboard.html");
            rd.forward(request, response);
        }
    }
}
