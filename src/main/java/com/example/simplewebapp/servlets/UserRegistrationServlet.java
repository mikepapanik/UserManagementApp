package com.example.simplewebapp.servlets;

import com.example.simplewebapp.util.DatabaseConnection;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * Servlet for user registration.
 */
@WebServlet("/registerUser")
public class UserRegistrationServlet extends HttpServlet {

    /**
     * Handles POST requests for user registration.
     *
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String gender = request.getParameter("gender");
        String birthdate = request.getParameter("birthdate");
        String workAddress = request.getParameter("workAddress");
        String homeAddress = request.getParameter("homeAddress");

        // Validate the inputs here (omitted for brevity)

        Connection conn = null;
        PreparedStatement userStmt = null;
        PreparedStatement homeAddressStmt = null;
        PreparedStatement workAddressStmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // Start transaction

            // Insert user
            String userSql = "INSERT INTO users (name, surname, gender, birthdate) VALUES (?, ?, ?, ?)";
            userStmt = conn.prepareStatement(userSql, Statement.RETURN_GENERATED_KEYS);
            userStmt.setString(1, name);
            userStmt.setString(2, surname);
            userStmt.setString(3, gender);
            userStmt.setDate(4, new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(birthdate).getTime()));
            userStmt.executeUpdate();

            // Get generated user ID
            ResultSet generatedKeys = userStmt.getGeneratedKeys();
            long userId = -1;
            if (generatedKeys.next()) {
                userId = generatedKeys.getLong(1);
            }

            if (userId != -1) {
                // Insert home address
                String homeAddressSql = "INSERT INTO home_address (user_id, homeAddress) VALUES (?, ?)";
                homeAddressStmt = conn.prepareStatement(homeAddressSql);
                homeAddressStmt.setLong(1, userId);
                homeAddressStmt.setString(2, homeAddress);
                homeAddressStmt.executeUpdate();

                // Insert work address
                String workAddressSql = "INSERT INTO work_address (user_id, workAddress) VALUES (?, ?)";
                workAddressStmt = conn.prepareStatement(workAddressSql);
                workAddressStmt.setLong(1, userId);
                workAddressStmt.setString(2, workAddress);
                workAddressStmt.executeUpdate();
            }

            conn.commit(); // Commit transaction
            request.setAttribute("successMessage", "Registration successful!");
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback transaction on error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            request.setAttribute("errorMessage", "Registration failed: " + e.getMessage());
        } finally {
            // Clean up
            try {
                if (userStmt != null) userStmt.close();
                if (homeAddressStmt != null) homeAddressStmt.close();
                if (workAddressStmt != null) workAddressStmt.close();
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
        dispatcher.forward(request, response);
    }
}






