<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.example.simplewebapp.util.DatabaseConnection" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Details</title>
    <link rel="stylesheet" type="text/css" href="css/userdetails.css" />
</head>
<body>
<div class="container">
    <h2>User Details</h2>
    <div class="user-details">
        <%
            String idParam = request.getParameter("id");
            if (idParam != null && idParam.matches("\\d+")) { // Check if id is a number
                int userId = Integer.parseInt(idParam);
                boolean dataFound = false;
                try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(
                             "SELECT u.*, ha.homeAddress AS home_address, wa.workAddress AS work_address " +
                                     "FROM users u " +
                                     "LEFT JOIN home_address ha ON u.id = ha.user_id " +
                                     "LEFT JOIN work_address wa ON u.id = wa.user_id " +
                                     "WHERE u.id = ?")) {
                    stmt.setInt(1, userId);
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            dataFound = true;
                            String name = rs.getString("name");
                            String surname = rs.getString("surname");
                            String gender = rs.getString("gender");
                            Date birthdate = rs.getDate("birthdate");
                            String workAddress = rs.getString("work_address");
                            String homeAddress = rs.getString("home_address");
        %>
        <p>ID: <%= userId %></p>
        <p>Name: <%= name %></p>
        <p>Surname: <%= surname %></p>
        <p>Gender: <%= gender %></p>
        <p>Birthdate: <%= birthdate %></p>
        <p>Work Address: <%= workAddress %></p>
        <p>Home Address: <%= homeAddress %></p>
        <%
                        }
                    }
                } catch (SQLException e) {
                    out.println("<p>Error retrieving user details: " + e.getMessage() + "</p>");
                }
                if (!dataFound) {
                    out.println("<p>User details not found for ID: " + userId + "</p>");
                }
            } else {
                out.println("<p>Invalid User ID</p>");
            }
        %>
    </div>
    <a href="userList.jsp" class="back-button">Back to User List</a>
</div>
</body>
</html>








