package com.hajres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJdbc {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/WorkersDB?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Sarajevo&characterEncoding=UTF-8";
        String user = "root";
        String password = "Dvcs01u+10ns";
        try {
            System.out.println("Connecting to database . . .");
            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
            System.out.println("Connection established.");
            System.out.println("Closing connection . . .");
            connection.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
