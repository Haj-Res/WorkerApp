package com.hajres.domain.dao;

import com.hajres.domain.model.Address;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class AddressDao extends Dao {
    private static final String FIND_ALL = "SELECT * FROM `address` ORDER BY `city`, `street`, `number`";
    private static final String FIND_BY_ID = "SELECT * FROM `address` WHERE `id_address`=?";
    private static final String FIND_BY_CITY = "SELECT * FROM address WHERE city LIKE ?";
    private static final String INSERT_ADDRESS = "INSERT INTO `address` (`city`, `street`, `number`) VALUES(?,?,?)" +
            " ON DUPLICATE KEY UPDATE id_address = LAST_INSERT_ID(`id_address`)";
    private static final String UPDATE_ADDRESS = "UPDATE `address` SET `city`=?, `street`=?, `number`=? WHERE `id_address`=?";
    private static final String DELETE_ADDRESS = "DELETE FROM `address` WHERE `id_address`=?";

    public AddressDao() {
    }

    public int add(Address address) {
        int id = 0;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(INSERT_ADDRESS, Statement.RETURN_GENERATED_KEYS);
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
        return id;
    }

    public void update(Address address) {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_ADDRESS);
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
            connection = getConnection();
            preparedStatement = connection.prepareStatement(DELETE_ADDRESS);
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

    public List<Address> findAll() {
        List<Address> addressList = new ArrayList<Address>();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(FIND_ALL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Address address = getLineFromResultSet();
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
            connection = getConnection();
            preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, idAddress);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                address = getLineFromResultSet();
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            cleanUp();
        }
        return address;
    }

    public List<Address> findByCity(String city) {
        List<Address> addressList = new ArrayList<>();
        city = '%' + city + '%';
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(FIND_BY_CITY);
            preparedStatement.setString(1, city);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                addressList.add(getLineFromResultSet());
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            cleanUp();
        }
        return addressList;
    }

    private Address getLineFromResultSet() throws SQLException {
        Address address = new Address();
        address.setIdAddress(resultSet.getInt("id_address"));
        address.setCity(resultSet.getString("city"));
        address.setStreet(resultSet.getString("street"));
        address.setNumber(resultSet.getString("number"));
        return address;
    }
}
