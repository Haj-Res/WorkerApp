package com.hajres;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

public class ConnectionFactory {
    private static String connectionUrl;
    private static String dbUser;
    private static String dbPassword;

    private static ConnectionFactory connectionFactory = null;
    private static LinkedList<Connection> connectionQueue;

    private ConnectionFactory() {
        Properties settings = getDbProperties();

        dbUser = settings.getProperty("user");
        dbPassword = settings.getProperty("password");
        connectionUrl = settings.getProperty("url");

        connectionQueue = new LinkedList<>();
        try {
            String driverClassName = "com.mysql.cj.jdbc.Driver";
            Class.forName(driverClassName);
            for (int i = 0; i < 10; i++) {
                addNewConnection();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        if (connectionQueue.isEmpty()) {
            addNewConnection();
        }
        return connectionQueue.poll();
    }

    public void returnConnection(Connection connection) {
        connectionQueue.add(connection);
    }

    private static void addNewConnection() throws SQLException {
        connectionQueue.add(DriverManager.getConnection(connectionUrl, dbUser, dbPassword));
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }

    private static Properties getDbProperties() {
        InputStream inputStream = null;
        Properties prop = new Properties();
        try {
            File file = new File("src/main/resources/db.properties");
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    prop.load(inputStream);
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }
}
