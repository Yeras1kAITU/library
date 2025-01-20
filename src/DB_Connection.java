import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connection {
    private static final String URL = "jdbc:postgresql://localhost:5432/library-db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "yerasylmcpe";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException e) {
            System.out.println("\nDisplaying borrowed books for User 1:");
            e.printStackTrace();
        }
        return conn;


    }
}
