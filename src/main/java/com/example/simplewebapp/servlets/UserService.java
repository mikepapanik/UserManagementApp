package com.example.simplewebapp.servlets;

import com.example.simplewebapp.model.User;
import com.example.simplewebapp.model.HomeAddress;
import com.example.simplewebapp.model.WorkAddress;
import com.example.simplewebapp.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Service class for retrieving user details from the database.
 */
public class UserService {

    /**
     * Retrieves user details by their unique ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return A User object containing the user's details, including home and work addresses,
     *         or null if the user with the specified ID was not found.
     */
    public User getUserDetailsById(int userId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.getConnection();
            // Updated query to join users, home_address, and work_address tables
            preparedStatement = connection.prepareStatement(
                    "SELECT u.id, u.name, u.surname, u.gender, u.birthdate, ha.homeAddress, wa.workAddress " +
                            "FROM users u " +
                            "LEFT JOIN home_address ha ON u.id = ha.user_id " +
                            "LEFT JOIN work_address wa ON u.id = wa.user_id " +
                            "WHERE u.id = ?");
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Create a User object with address details
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("gender").charAt(0),
                        resultSet.getDate("birthdate")
                );

                // Create HomeAddress and WorkAddress objects
                HomeAddress homeAddress = new HomeAddress(resultSet.getString("homeAddress"));
                WorkAddress workAddress = new WorkAddress(resultSet.getString("workAddress"));

                // Set the addresses for the user
                user.setHomeAddress(homeAddress);
                user.setWorkAddress(workAddress);

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors here, log them, or throw an exception as needed.
        } finally {
            // Ensure database resources are closed even if an exception occurs
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Return null if the user with the specified ID was not found.
        return null;
    }
}





