package com.hajres.domain;


import com.hajres.domain.model.Address;
import com.hajres.domain.model.Company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompanyDao extends Dao {
    public CompanyDao() {
    }

    public int add(Company company) {
        int id = 0;
        try {
            String queryString = "INSERT INTO `company` (`name`, `idAddress`) VALUES (?, ?)";

            connection = getConnection();

            preparedStatement = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, company.getName());

            if (company.getAddress() == null) {
                throw new SQLException("Creating company failed. Company needs to have an address.");
            } else {
                AddressDao addressDao = new AddressDao();

                Address temp = addressDao.checkIfExists(company.getAddress());
                if (temp == null) {
                    int idAddress = addressDao.add(company.getAddress());
                    if (idAddress == 0) {
                        throw new SQLException("Creating company address failed, no address ID obtained.");
                    }
                    preparedStatement.setInt(2, idAddress);
                } else {
                    preparedStatement.setInt(2, temp.getIdAddress());
                }

            }

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating company failed, no rows affected.");
            }

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating company failed, no ID obtained.");

            }
            System.out.println("Company added successfully!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            cleanUp();
        }
        return id;
    }

//    public Company checkIfExists(Company company) {
//        Company result = new Company();
//        try {
//            String queryString = "SELECT * FROM `company` " +
//                    "WHERE  `name`=?";
//            if (company.getAddress() != null) {
//                queryString += " AND `idAddress`=?";
//            }
//            connection = getConnection();
//            preparedStatement = connection.prepareStatement(queryString);
//        }
//    }
}
