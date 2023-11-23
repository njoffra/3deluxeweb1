//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Servlets;

import Beans.User;
import Utils.DB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(
        name = "RegisterServlet",
        value = {"/register"}
)
public class RegisterServlet extends HttpServlet {
    public RegisterServlet() {
    }

    private boolean registerUser(User user) {
        try {
            Connection con = DB.getInstance().getConnection();

            boolean var6;
            label74: {
                boolean var3;
                try {
                    if (con != null) {
                        String query = "INSERT INTO users (username, email, password, address, role) VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement preparedStatement = con.prepareStatement(query);

                        try {
                            preparedStatement.setString(1, user.getUsername());
                            preparedStatement.setString(2, user.getEmail());
                            preparedStatement.setString(3, user.getPassword());
                            preparedStatement.setString(4, user.getAddress());
                            preparedStatement.setInt(5, user.getRole());
                            int rowsAffected = preparedStatement.executeUpdate();
                            var6 = rowsAffected > 0;
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
                        break label74;
                    }

                    var3 = false;
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

                return var3;
            }

            if (con != null) {
                con.close();
            }

            return var6;
        } catch (SQLException var11) {
            var11.printStackTrace();
            return false;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String address = request.getParameter("address");
            if (password.equals(confirmPassword)) {
                User newUser = new User();
                newUser.setUsername(username);
                newUser.setEmail(email);
                newUser.setPassword(password);
                newUser.setAddress(address);
                newUser.setRole(1);
                boolean isRegistered = this.registerUser(newUser);
                if (isRegistered) {
                    out.println("Registration successful!");
                    response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
                } else {
                    out.println("Registration failed. Please try again.");
                }
            } else {
                out.println("Passwords do not match. Please try again.");
            }
        } catch (Throwable var12) {
            if (out != null) {
                try {
                    out.close();
                } catch (Throwable var11) {
                    var12.addSuppressed(var11);
                }
            }

            throw var12;
        }

        if (out != null) {
            out.close();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/pages/register.jsp");
    }
}
