package com.hajres.domain.dao;

import com.hajres.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public abstract class Dao {
    protected Connection connection = null;
    protected PreparedStatement preparedStatement = null;
    protected ResultSet resultSet = null;

    protected void cleanUp() {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                returnConnection(connection);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    protected Connection getConnection() throws SQLException {
        return ConnectionFactory.getInstance().getConnection();
    }

    protected void returnConnection(Connection  connection) {
        ConnectionFactory.getInstance().returnConnection(connection);
    }

}
