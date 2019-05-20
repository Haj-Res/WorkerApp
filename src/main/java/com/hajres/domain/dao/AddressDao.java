package com.hajres.domain.dao;

import com.hajres.domain.model.Address;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AddressDao extends Dao {

    public AddressDao() {
    }

    private Address readFromResultSet() throws SQLException {
        Address address = new Address();
        address.setIdAddress(resultSet.getInt("idAddress"));
        address.setCity(resultSet.getString("city"));
        address.setStreet(resultSet.getString("street"));
        address.setNumber(resultSet.getString("number"));
        return address;
    }

    public int add(Address address) {
        int id = 0;

        // Check if address exists. Return idAddress if it exists, otherwise
        // insert address and return id of if new row
        Address temp = checkIfExists(address);
        if (temp != null) {
            return temp.getIdAddress();
        } else {
            try {
                String queryString = "INSERT INTO `address` (`city`, `street`, `number`) VALUES(?,?,?)";
                connection = getConnection();
                preparedStatement = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, address.getCity());
                preparedStatement.setString(2, address.getStreet());
                preparedStatement.setString(3, address.getNumber());

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating address failed, no rows affected.");
                }

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating address failed, no ID obtained.");

                }
                System.out.println("Address added successfully!");
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
//                e.printStackTrace();
            } finally {
                cleanUp();
            }
        }
        return id;
    }

    public void update(Address address) {
        try {
            String queryString = "UPDATE `address` SET `city`=?, `street`=?, `number`=? WHERE `idAddress`=?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, address.getCity());
            preparedStatement.setString(2, address.getStreet());
            preparedStatement.setString(3, address.getNumber());
            preparedStatement.setInt(4, address.getIdAddress());
            preparedStatement.executeUpdate();
            System.out.println("Address updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error while updating address!");
            e.printStackTrace();
        } finally {
            cleanUp();
        }
    }

    public void delete(int idAddress) {
        try {
            String queryString = "DELETE FROM `address` WHERE `idAddress`=?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, idAddress);
            preparedStatement.executeUpdate();
            System.out.println("Address deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error while deleting address!");
            e.printStackTrace();
        } finally {
            cleanUp();
        }
    }

    public ArrayList<Address> findAll() {
        ArrayList<Address> addressList = new ArrayList<Address>();
        try {
            String queryString = "SELECT * FROM `address`";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Address address = readFromResultSet();
                addressList.add(address);
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching all addresses!");
            e.printStackTrace();
        } finally {
            cleanUp();
        }
        return addressList;
    }

    public Address findById(int idAddress) {
        Address address = new Address();
        try {
            String queryString = "SELECT * FROM `address` WHERE `idAddress`=?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, idAddress);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                address.setIdAddress(resultSet.getInt("idAddress"));
                address.setCity(resultSet.getString("city"));
                address.setStreet(resultSet.getString("street"));
                address.setNumber(resultSet.getString("number"));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            cleanUp();
        }
        return address;
    }

    public ArrayList<Address> findByCity(String city) {
        ArrayList<Address> addressList = new ArrayList<>();
        city = '%' + city + '%';
        try {
            String queryString = "SELECT * FROM address WHERE city LIKE ?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, city);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Address address = readFromResultSet();
                addressList.add(address);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            cleanUp();
        }
        return addressList;
    }

    public Address checkIfExists(Address address) {
        Address result = new Address();
        try {
            String queryStrying = "SELECT * FROM `address` " +
                    "WHERE `city`=? AND `street`=? AND `number`=?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryStrying);
            preparedStatement.setString(1, address.getCity());
            preparedStatement.setString(2, address.getStreet());
            preparedStatement.setString(3, address.getNumber());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                result.setIdAddress(resultSet.getInt("idAddress"));
                result.setCity(resultSet.getString("city"));
                result.setStreet(resultSet.getString("street"));
                result.setNumber(resultSet.getString("number"));
                System.out.println(address.toString());
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
