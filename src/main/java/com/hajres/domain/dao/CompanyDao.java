package com.hajres.domain.dao;


import com.hajres.domain.model.Address;
import com.hajres.domain.model.Company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyDao extends Dao {

    public CompanyDao() {
    }

    private Company readFromResultSet() throws SQLException {
        Company company = new Company();

        company.setIdCompany(resultSet.getInt("idCompany"));
        company.setName(resultSet.getString("name"));
        AddressDao addressDao = new AddressDao();
        company.setAddress(addressDao.findById(resultSet.getInt("idAddress")));

        return company;
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
            // Check if company with inserted address exists
            // Return idCompany if it exists, otherwise insert it and return new idCompany
            Company temp = checkIfExists(company);
            if (temp != null) {
                idCompany = temp.getIdCompany();
            } else {
                String queryString = "INSERT INTO `company` (`name`, `idAddress`) VALUES (?, ?)";
                connection = getConnection();

                preparedStatement = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
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
            }
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
            if(company.getAddress() == null) {
                throw new SQLException("Address can't be null");
            }

            if (company.getAddress().getIdAddress()==0) {
                AddressDao addressDao = new AddressDao();
                int id = addressDao.add(company.getAddress());
                company.getAddress().setIdAddress(id);
            }
            String queryString = "UPDATE `company` SET `name`=?, `idAddress`=? WHERE `idCompany`=?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
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
            String queryString = "DELETE FROM `company` WHERE `idCompany`=?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
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
            String queryString = "SELECT * FROM `company` " +
                    "LEFT JOIN address a ON company.idAddress = a.idAddress ORDER BY `name`";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();

            while (resultSet. next()) {
                Company company;
                company = readFromResultSet();
                companyList.add(company);
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
            String queryString = "SELECT * FROM `company` WHERE `idCompany`=?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
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
        city ='%' + city + '%';
        try {
            String queryString = "SELECT idCompany, name, c.idAddress FROM company c LEFT JOIN address a on a.idAddress = c.idAddress WHERE a.city LIKE ?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, city);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Company company;
                company = readFromResultSet();
                companyList.add(company);
            }

        } catch (SQLException e) {
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
            String queryString = "SELECT * FROM `company` WHERE `name` LIKE ?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Company company;
                company = readFromResultSet();
                companyList.add(company);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            cleanUp();
        }
        return companyList;
    }

    private Company checkIfExists(Company company) {
        Company result = new Company();
        try {
            String queryString = "SELECT * FROM `company` " +
                    "WHERE  `name`=? AND `idAddress`=?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, company.getName());
            preparedStatement.setObject(2, company.getAddress().getIdAddress());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result.setIdCompany(resultSet.getInt("idCompany"));
                result.setName(resultSet.getString("name"));
                Address address = new Address();
                address.setIdAddress(resultSet.getInt("idAddress"));
                result.setAddress(address);
            } else {
                result = null;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cleanUp();
        }
        return result;
    }
}
