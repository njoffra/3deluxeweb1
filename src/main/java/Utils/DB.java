//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
        } catch (Exception var2) {
            var2.printStackTrace();
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
