package src.happymeal.connection;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionUtility {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/happymeal?" +
                    "user=superuser&password=password");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
