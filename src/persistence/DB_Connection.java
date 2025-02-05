package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connection {
    private static final String URL = "jdbc:postgresql://localhost:5432/library-db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "yerasylmcpe";

    private static DB_Connection instance;
    private Connection connection;

    private DB_Connection() {}

    public static synchronized DB_Connection getInstance() {
        if (instance == null) {
            instance = new DB_Connection();
        }
        return instance;
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}