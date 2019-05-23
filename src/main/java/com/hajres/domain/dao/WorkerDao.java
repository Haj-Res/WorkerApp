package com.hajres.domain.dao;

import com.hajres.domain.model.Worker;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkerDao extends Dao {


    private static final String SELECT_WORKER = "SELECT * FROM `worker` order by `firstName`, `lastName`";
    private static final String SELECT_WORKER_FROM_TO = "SELECT * FROM `worker` order by `firstName`, `lastName` LIMIT ?, ?";
    private static final String FIND_BY_ID = "SELECT * FROM `worker` WHERE `JMBG`=?";

    private static final String INSERT_WORKER = "INSERT INTO `worker` " +
            "(JMBG, firstName, lastName, birthDate, idCompany, idAddress)" +
            "VALUES (?, ?, ?, ?, ?, ?)";


    public WorkerDao() {
    }

    public int add(Worker worker) {
        int id = 0;
        int idAddress;
        Integer idCompany;
        try {
            idAddress = getAddressId(worker);
            idCompany = getCompanyId(worker);

            connection = getConnection();
            preparedStatement = connection.prepareStatement(INSERT_WORKER);
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

    public void update(Worker worker, String oldJmbg) {

        int idAddress;
        Integer idCompany;
        try {
            idAddress = getAddressId(worker);
            idCompany = getCompanyId(worker);

            String queryString = "UPDATE `worker` set `JMBG`=?, `firstName`=?, `lastName`=?, `birthDate`=?," +
                    " `idAddress`=?, `idCompany`=? WHERE `JMBG`=?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, worker.getJmbg());
            preparedStatement.setString(2, worker.getFirstName());
            preparedStatement.setString(3, worker.getLastName());
            preparedStatement.setDate(4, worker.getBirthDate());
            preparedStatement.setInt(5, idAddress);
            preparedStatement.setObject(6, idCompany);
            preparedStatement.setString(7, oldJmbg);

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

    public List<Worker> findAll(int from, int to) {
        List<Worker> workerList = null;
        try {
            connection = getConnection();

            if (from != -1 && to != -1) {
                preparedStatement = connection.prepareStatement(SELECT_WORKER_FROM_TO);
            } else {
                preparedStatement = connection.prepareStatement(SELECT_WORKER);
            }
            if (from != -1 && to != -1) {
                preparedStatement.setInt(1, from);
                preparedStatement.setInt(2, to);
            }
            resultSet = preparedStatement.executeQuery();
            workerList = new ArrayList<>();
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

    public List<Worker> findAll() {
        return findAll(-1, -1);
    }

    public Worker findById(String jmbg) {
        Worker worker = new Worker();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(FIND_BY_ID);
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

    private int getAddressId(Worker worker) throws SQLException {
        int idAddress;
        AddressDao addressDao = new AddressDao();
        idAddress = addressDao.add(worker.getAddress());
        if (idAddress == 0) {
            throw new SQLException("Creating user failed, no address ID obtained.");
        }
        return idAddress;
    }

    private Integer getCompanyId(Worker worker) throws SQLException {
        Integer idCompany;

        if (worker.getCompany() == null || worker.getCompany().getName() == null) {
            idCompany = null;
        } else if (worker.getCompany().getIdCompany() == 0) {
            CompanyDao companyDao = new CompanyDao();
            idCompany = companyDao.add(worker.getCompany());
            if (idCompany == 0) {
                throw new SQLException("Creating user failed, no company ID obtained.");
            }
        } else {
            idCompany = worker.getCompany().getIdCompany();
        }
        return idCompany;
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
