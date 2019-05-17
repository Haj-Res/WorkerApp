package com.hajres.domain.dao;

import com.hajres.domain.model.Worker;

import java.sql.*;
import java.util.ArrayList;

public class WorkerDao extends Dao {


    public WorkerDao() {
    }


    public int add(Worker worker) {
        int id = 0;
        int idAddress;
        Integer idCompany;
        try {

            if (worker.getAddress() == null) {
                throw new SQLException("Creating worker failed. Worker need an address.");
            } else {
                AddressDao addressDao = new AddressDao();
                idAddress = addressDao.add(worker.getAddress());
                if (idAddress == 0) {
                    throw new SQLException("Creating user failed, no address ID obtained.");
                }
            }

            if (worker.getCompany() == null) {
                idCompany = null;
            } else {
                CompanyDao companyDao = new CompanyDao();
                idCompany = companyDao.add(worker.getCompany());
                if (idCompany == 0) {
                    throw new SQLException("Creating user failed, no company ID obtained.");
                }
            }


            String queryString = "INSERT INTO `worker` " +
                    "(JMBG, firstName, lastName, birthDate, idCompany, idAddress)" +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, worker.getJmbg());
            preparedStatement.setString(2, worker.getFirstName());
            preparedStatement.setString(3, worker.getLastName());
            preparedStatement.setDate(4, worker.getBirthDate());
            preparedStatement.setObject(5, idCompany);
            preparedStatement.setInt(6, idAddress);


            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows were affected.");
            } else {
                id = 1;
            }
            System.out.println("Worker added successfully!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cleanUp();
        }
        return id;
    }

    public void update(Worker worker) {

        try {
            String queryString = "UPDATE `worker` set `firstName`=?, `lastName`=?, `birthDate`=?," +
                    " `idAddress`=?, `idCompany`=? WHERE `JMBG`=?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, worker.getFirstName());
            preparedStatement.setString(2, worker.getLastName());
            preparedStatement.setDate(3, worker.getBirthDate());
            preparedStatement.setInt(4, worker.getAddress().getIdAddress());
            preparedStatement.setInt(5, worker.getCompany().getIdCompany());
            preparedStatement.setString(6, worker.getJmbg());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating worker failed. No rows were affected.");
            }
            System.out.println("Updated " + affectedRows + " rows.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cleanUp();
        }
    }

    public void delete(String jmbg) {
        try {
            String queryString = "DELETE FROM `worker` WHERE `JMBG`=?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, jmbg);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting worker failed. No rows were affected");
            }
            System.out.println("Deleted " + affectedRows + " rows.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            cleanUp();
        }
    }

    public ArrayList<Worker> findAll() {
        ArrayList<Worker> workerList = null;
        try {
            String queryString = "SELECT * FROM `worker`";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            workerList = new ArrayList<Worker>();
            while (resultSet.next()) {
                Worker worker;
                worker = readFromResultSet();
                workerList.add(worker);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            cleanUp();
        }
        return workerList;
    }

    public Worker findById(String jmbg) {
        Worker worker = new Worker();
        try {
            String queryString = "SELECT * FROM `worker` WHERE `JMBG`=?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, jmbg);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                worker = readFromResultSet();
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cleanUp();
        }
        return worker;
    }

    private Worker readFromResultSet() throws SQLException {
        Worker worker = new Worker();
        worker.setJmbg(resultSet.getString("JMBG"));
        worker.setFirstName(resultSet.getString("firstName"));
        worker.setLastName(resultSet.getString("lastName"));
        worker.setBirthDate(resultSet.getDate("birthDate").toLocalDate());

        AddressDao addressDao = new AddressDao();
        worker.setAddress(addressDao.findById(resultSet.getInt("idAddress")));

        CompanyDao companyDao = new CompanyDao();
        worker.setCompany(companyDao.findById(resultSet.getInt("idCompany")));
        return worker;
    }
}
