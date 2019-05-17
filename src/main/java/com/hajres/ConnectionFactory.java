package com.hajres;

import java.io.*;
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
        Properties settings = getDbProperties();

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
        return conn;
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
