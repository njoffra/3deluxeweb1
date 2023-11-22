package Servlets;

import Beans.User;
import Utils.DB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(
        name = "LoginServlet",
        value = {"/login"}
)
public class LoginServlet extends HttpServlet {
    public LoginServlet() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        if (validateUser(user)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            int userRole = user.getRole();
            session.setAttribute("role", userRole);
            response.sendRedirect(request.getContextPath() + "/home.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp?error=login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
    }

    private boolean validateUser(User user) {
        try {
            Connection con = DB.getInstance().getConnection();

            boolean result;

            try {
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");

                try {
                    preparedStatement.setString(1, user.getUsername());
                    preparedStatement.setString(2, user.getPassword());
                    ResultSet resultSet = preparedStatement.executeQuery();
                    result = resultSet.next();
                } catch (Throwable var9) {
                    if (preparedStatement != null) {
                        try {
                            preparedStatement.close();
                        } catch (Throwable var8) {
                            var9.addSuppressed(var8);
                        }
                    }

                    throw var9;
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Throwable var10) {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Throwable var7) {
                        var10.addSuppressed(var7);
                    }
                }

                throw var10;
            }

            if (con != null) {
                con.close();
            }

            return result;
        } catch (SQLException var11) {
            var11.printStackTrace();
            return false;
        }
    }
}
