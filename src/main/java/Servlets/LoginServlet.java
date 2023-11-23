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

        User authenticatedUser = validateUser(username, password);

        if (authenticatedUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", authenticatedUser);
            int userRole = authenticatedUser.getRole();
            session.setAttribute("role", userRole);

            System.out.println("User Role: " + userRole);

            if (userRole == 2) {
                response.sendRedirect(request.getContextPath() + "/pages/admin.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/home.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp?error=login");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
    }

    private User validateUser(String username, String password) {
        User user = null;

        try {
            Connection con = DB.getInstance().getConnection();

            try {
                PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    user = new User();
                    user.setUsername(resultSet.getString("username"));
                    user.setEmail(resultSet.getString("email"));
                    user.setAddress(resultSet.getString("address"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(resultSet.getShort("role"));
                }
            } finally {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

}
