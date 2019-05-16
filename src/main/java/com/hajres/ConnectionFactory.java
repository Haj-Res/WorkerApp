package com.hajres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private String connectionUrl;
    private String dbUser;
    private String dbPassword;

    private static ConnectionFactory connectionFactory = null;

    private ConnectionFactory() {
        Properties settings = Main.settings;

        this.dbUser = settings.getProperty("user");
        this.dbPassword = settings.getProperty("password");
        this.connectionUrl = settings.getProperty("url");

        try {
            String driverClassName = "com.mysql.cj.jdbc.Driver";
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(connectionUrl, dbUser, dbPassword);
        return  conn;
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        return  connectionFactory;
    }
}
