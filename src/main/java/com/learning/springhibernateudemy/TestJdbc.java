package com.learning.springhibernateudemy;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

    public static void main(String... args) {
        String jdbcUrl = "jdbc:sqlserver://localhost:1433;databaseName=springhibernate";
        String user = "sa";
        String pwd = "Ionuser@123";
        try {
            System.out.println("Connecting to database " + jdbcUrl);

            Connection conn = DriverManager.getConnection(jdbcUrl, user, pwd);
            conn.close();

            System.out.println("Connection successful!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}