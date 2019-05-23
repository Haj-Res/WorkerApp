package com.hajres.domain.dao;


import com.hajres.domain.model.Address;
import com.hajres.domain.model.Company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyDao extends Dao {
    private static final String FIND_ALL = "SELECT * FROM `company` ORDER BY `name`";
    private static final String FIND_BY_ID = "SELECT * FROM `company` WHERE `idCompany`=?";
    private static final String FIND_BY_CITY = "SELECT idCompany, name, c.idAddress FROM company c LEFT JOIN address a on a.idAddress = c.idAddress WHERE a.city LIKE ?";
    private static final String FIND_BY_NAME = "SELECT * FROM `company` WHERE `name` LIKE ?";
    private static final String FIND_IN_ALL = "SELECT idCompany, name, c.idAddress FROM company c " +
            "LEFT JOIN address a on a.idAddress = c.idAddress " +
            "WHERE c.name LIKE ? OR a.city LIKE ? OR a.street LIKE ? OR a.number LIKE ? ORDER BY name";
    private static final String INSERT_COMPANY = "INSERT INTO `company` (`name`, `idAddress`) VALUES (?, ?)" +
            " ON DUPLICATE KEY UPDATE idCompany = LAST_INSERT_ID(idCompany)";
    private static final String UPDATE_COMPANY = "UPDATE `company` SET `name`=?, `idAddress`=? WHERE `idCompany`=?";
    private static final String DELETE_COMPANY = "DELETE FROM `company` WHERE company.`idCompany`=?";

    public CompanyDao() {
    }

    public int add(Company company) {
        int idCompany = 0;
        int idAddress;
        try {
            // Throw exception is address is null, else add it and get its ID back
            if (company.getAddress() == null) {
                throw new SQLException("Creating company failed. Company needs to have an address.");
            } else {
                AddressDao addressDao = new AddressDao();
                idAddress = addressDao.add(company.getAddress());
                if (idAddress == 0) {
                    throw new SQLException("Creating company address failed, no address ID obtained.");
                }
                company.getAddress().setIdAddress(idAddress);
            }

            connection = getConnection();
            preparedStatement = connection.prepareStatement(INSERT_COMPANY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, company.getName());
            preparedStatement.setInt(2, idAddress);


            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating company failed, no rows affected.");
            }

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                idCompany = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating company failed, no ID obtained.");

            }
            System.out.println("Company added successfully!");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cleanUp();
        }
        return idCompany;
    }

    public void update(Company company) {
        try {
            if (company.getAddress() == null) {
                throw new SQLException("Address can't be null");
            }

            if (company.getAddress().getIdAddress() == 0) {
                AddressDao addressDao = new AddressDao();
                int id = addressDao.add(company.getAddress());
                company.getAddress().setIdAddress(id);
            }
            connection = getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_COMPANY);
            preparedStatement.setString(1, company.getName());
            preparedStatement.setInt(2, company.getAddress().getIdAddress());
            preparedStatement.setInt(3, company.getIdCompany());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating company failed. No rows were affected.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            cleanUp();
        }
    }

    public void delete(int idCompany) {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(DELETE_COMPANY);
            preparedStatement.setInt(1, idCompany);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting company with ID:" + idCompany + " failed. No rows were affected.");
            }
            System.out.println("Company with ID:" + idCompany + " deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            cleanUp();
        }
    }

    public List<Company> findAll() {
        List<Company> companyList = new ArrayList<Company>();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(FIND_ALL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                companyList.add(getLineFromResultSet());
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            cleanUp();
        }
        return companyList;
    }

    public Company findById(int idCompany) {
        Company company = new Company();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, idCompany);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                company.setIdCompany(resultSet.getInt("idCompany"));
                company.setName(resultSet.getString("name"));
                AddressDao addressDao = new AddressDao();
                company.setAddress(addressDao.findById(resultSet.getInt("idAddress")));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            cleanUp();
        }
        return company;
    }

    public List<Company> findByCity(String city) {
        List<Company> companyList = new ArrayList<>();
        city = '%' + city + '%';
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(FIND_BY_CITY);
            preparedStatement.setString(1, city);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                companyList.add(getLineFromResultSet());
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            cleanUp();
        }
        return companyList;
    }

    public List<Company> findByAll(String filter) {
        List<Company> companyList = new ArrayList<>();
        filter = "%" + filter + "%";

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(FIND_IN_ALL);
            preparedStatement.setString(1, filter);
            preparedStatement.setString(2, filter);
            preparedStatement.setString(3, filter);
            preparedStatement.setString(4, filter);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                companyList.add(getLineFromResultSet());
            }
        } catch (SQLException e) {
            companyList = null;
            System.out.println("Error: " + e.getMessage());
        } finally {
            cleanUp();
        }
        return companyList;

    }

    public List<Company> findByName(String name) {
        List<Company> companyList = new ArrayList<Company>();
        name = '%' + name + '%';
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(FIND_BY_NAME);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Company company;
                company = getLineFromResultSet();
                companyList.add(company);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            cleanUp();
        }
        return companyList;
    }

    private Company getLineFromResultSet() throws SQLException {
        Company company = new Company();

        company.setIdCompany(resultSet.getInt("idCompany"));
        company.setName(resultSet.getString("name"));
        AddressDao addressDao = new AddressDao();
        company.setAddress(addressDao.findById(resultSet.getInt("idAddress")));

        return company;
    }
}
