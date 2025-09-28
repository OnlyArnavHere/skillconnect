package com.skillconnect.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    // --- Database Credentials ---
    // Make sure the database 'skillconnect_db' exists
    private static final String URL = "jdbc:mysql://localhost:3306/skillconnect_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "your_db_username"; // <-- CHANGE THIS
    private static final String PASSWORD = "your_db_password"; // <-- CHANGE THIS

    public static Connection getConnection() throws SQLException {
        try {
            // Register the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("MySQL JDBC Driver not found.", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
