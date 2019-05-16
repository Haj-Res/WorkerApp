package com.hajres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private String driverClassName = "com.mysql.cj.jdbc.Driver";
    private String connectionUrl;
    private String dbUser;
    private String dbPassword;

    private static ConnectionFactory connectionFactory = null;

    private ConnectionFactory(Properties settings) {
        this.dbUser = settings.getProperty("user");
        this.dbPassword = settings.getProperty("password");
        this.connectionUrl = settings.getProperty("url");
        try {
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

    public static ConnectionFactory getInstance(Properties settings) {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory(settings);
        }
        return  connectionFactory;
    }
}
