package com.example.simplewebapp.servlets;

import com.example.simplewebapp.model.User;
import com.example.simplewebapp.model.HomeAddress;
import com.example.simplewebapp.model.WorkAddress;
import com.example.simplewebapp.util.DatabaseConnection;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet to retrieve a list of users from the database and forward them to userList.jsp.
 */
@WebServlet("/userList")
public class UserListServlet extends HttpServlet {

    /**
     * Handles GET requests to retrieve and display a list of users.
     *
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<User> users = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT u.id, u.name, u.surname, u.gender, u.birthdate, ha.homeAddress, wa.workAddress " +
                             "FROM users u " +
                             "LEFT JOIN home_address ha ON u.id = ha.user_id " +
                             "LEFT JOIN work_address wa ON u.id = wa.user_id")) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
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

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Error retrieving users from database", e);
        }

        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("userList.jsp");
        dispatcher.forward(request, response);
    }
}

