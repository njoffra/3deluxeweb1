//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Utils;

import java.sql.*;

public class DB {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/1DB_FranjoSeminErnes";
    private static final String USERNAME = "franjo";
    private static final String PASSWORD = "password";
    private static DB instance;
    private Connection con;

    private DB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/1DB_FranjoSeminErnes", "franjo", "password");
            if (!adminUserExists(con)) {

                insertAdminUser(con);
            }
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }
    private boolean adminUserExists(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM users WHERE username = 'admin'");
            resultSet.next();
            int count = resultSet.getInt(1);
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void insertAdminUser(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            int rowsAffected = statement.executeUpdate("INSERT INTO users (username, email, password, address, role) VALUES ('admin', 'admin@example.com', 'adminpassword', 'Admin Address', 2)");
            if (rowsAffected > 0) {
                System.out.println("Admin user inserted successfully.");
            } else {
                System.err.println("Failed to insert admin user.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error inserting admin user.");
        }
    }

    public static synchronized DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }

        return instance;
    }

    public Connection getConnection() {
        return this.con;
    }

    public boolean isConnected() {
        try {
            return this.con != null && !this.con.isClosed();
        } catch (SQLException var2) {
            var2.printStackTrace();
            return false;
        }
    }
}
