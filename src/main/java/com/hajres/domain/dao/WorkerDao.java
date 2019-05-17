package com.hajres.domain;

import com.hajres.domain.model.Address;
import com.hajres.domain.model.Worker;

import java.sql.*;

public class WorkerDao extends Dao{

    public WorkerDao() {
    }


    public int add(Worker worker) {
        int id = 0;
        try {
            String queryString = "INSERT INTO `worker` (JMBG, firstName, lastName, birthDate, idCompany, idAddress)" +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, worker.getJmbg());
            preparedStatement.setString(2, worker.getFirstName());
            preparedStatement.setString(3, worker.getLastName());
            preparedStatement.setDate(4, worker.getBirthDate());
            if (worker.getCompany() == null) {
                preparedStatement.setObject(5, null);
            } else {
                CompanyDao companyDao = new CompanyDao();

            }

            if (worker.getAddress() == null) {
                throw new SQLException("Creating worker failed. Worker need an address.");
            } else {
                AddressDao addressDao = new AddressDao();
                Address temp = addressDao.checkIfExists(worker.getAddress());
                if (temp == null) {
                    int idAddress = addressDao.add(worker.getAddress());
                    if (idAddress == 0) {
                        throw new SQLException("Creating user failed, no address ID obtained.");
                    } else {
                        preparedStatement.setInt(6, idAddress);
                    }
                } else {
                    preparedStatement.setInt(6, temp.getIdAddress());
                }
            }

            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows were affected.");
            } else {
                id = 1;
            }
            System.out.println("Worker added successfully!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            cleanUp();
        }
        return id;
    }

    private void update(Worker worker) {
        try {
            String queryString = "UPDATE `worker` set `firstName`=?, `lastName`=?, `birthDate`=?," +
                    "`idCompany`=?, `idAddress`=? WHERE `JMBG`=?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, worker.getFirstName());
            preparedStatement.setString(2, worker.getLastName());
            preparedStatement.setDate(3, worker.getBirthDate());
            Integer idCompany = worker.getCompany() == null ? null : worker.getCompany().getIdCompany();
            preparedStatement.setInt(4, idCompany);
            Integer idAddress = worker.getAddress() == null ? null : worker.getAddress().getIdAddress();
            preparedStatement.setInt(5, idAddress);
            preparedStatement.setString(6, worker.getJmbg());

            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            System.out.println("Error while updating worker!");
            e.printStackTrace();
        }
    }
}
