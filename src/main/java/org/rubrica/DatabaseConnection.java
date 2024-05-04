package org.rubrica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {

        String url = "jdbc:mysql://127.0.0.1:3306/rubrica";
        String username = "root";
        String password = "Password12@";

        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
}

