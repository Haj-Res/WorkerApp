package com.hajres;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
       Properties prop = getDbProperties();
        ConnectionFactory connectionFactory = ConnectionFactory.getInstance(prop);
        Connection conn;
        Statement statement;
        try {
            conn = connectionFactory.getConnection();

            statement = conn.createStatement();
            String sql;
            sql = "SELECT * FROM worker";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String jmbg = rs.getString("jmbg");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                Date bDay = rs.getDate("birthDate");
                int companyId = rs.getInt("idCompany");

                System.out.println("JMBG: " + jmbg);
                System.out.println("First name: " + firstName);
                System.out.println("Last name: " + lastName);
                System.out.println("Birth date: " + bDay);
                System.out.println("Company id: " + companyId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            if(inputStream!=null) {
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
