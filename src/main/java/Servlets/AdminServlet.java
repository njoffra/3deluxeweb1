package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AdminServlet", value = "/admin")
public class AdminServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        boolean isAdmin = checkAdminStatus(session);

        if (isAdmin) {
            response.getWriter().println("Welcome, Admin!");
            response.sendRedirect(request.getContextPath()+ "/pages/admin.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp?error=admin");
        }
    }

    private boolean checkAdminStatus(HttpSession session) {
        Object roleAttribute = session.getAttribute("role");

        return roleAttribute != null && roleAttribute.equals(2);
    }
}
