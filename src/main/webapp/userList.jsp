<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.simplewebapp.model.User" %>
<%@ page import="com.example.simplewebapp.util.DatabaseConnection" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>User List</title>
    <link rel="stylesheet" type="text/css" href="css/userlist.css" />
</head>
<body>
<div class="container">
    <h2>User List</h2>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Surname</th>
            <!-- Add other table headers if needed -->
        </tr>
        <%
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users");
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int userId = rs.getInt("id");
                    String name = rs.getString("name");
                    String surname = rs.getString("surname");
                    out.println("<tr onclick=\"window.open('userDetails.jsp?id=" + userId + "', '_blank');\">");
                    out.println("<td>" + name + "</td>");
                    out.println("<td>" + surname + "</td>");
                    out.println("</tr>");
                }
            } catch (SQLException e) {
                out.println("Database connection problem: " + e.getMessage());
            }
        %>
    </table>
    <button class="nav-button" onclick="window.location.href='index.jsp'">Home</button>
    <button class="nav-button" onclick="window.location.href='register.jsp'">Register User</button>
</div>
</body>
</html>


