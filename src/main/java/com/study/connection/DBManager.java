package com.study.connection;

import java.sql.*;

public class DBManager {
    private static final String URL = "jdbc:mysql://localhost:3306/ebrainsoft_study?useUnicode=true&characterEncoding=UTF-8";
    private static final String USER = "ebsoft";
    private static final String PASSWORD = "ebsoft";

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
