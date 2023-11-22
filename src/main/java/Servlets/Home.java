//


package Servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "home",
        value = {"/", "/home.jsp"}
)
public class Home extends HttpServlet {
    public Home() {
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            HttpSession session = request.getSession();
            Object userObject = session.getAttribute("user");
            RequestDispatcher rd = request.getRequestDispatcher("pages/home.jsp");
            rd.forward(request, response);
        } catch (Throwable var8) {
            if (out != null) {
                try {
                    out.close();
                } catch (Throwable var7) {
                    var8.addSuppressed(var7);
                }
            }

            throw var8;
        }

        if (out != null) {
            out.close();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    public String getServletInfo() {
        return "Short description";
    }
}
