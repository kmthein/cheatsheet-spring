package com.spring.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    static Connection con = null;
    public static Connection getConn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String db = "jdbc:mysql://localhost:3306/cheatsheet";
            String username = "root";
            String password = "12345";
            con = DriverManager.getConnection(db, username, password);
            System.out.println("Database Connecting.....");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver class not found");
        } catch (SQLException e) {
            System.out.println("Database connection is not OK" + e);
        }
        return con;
    }
}
