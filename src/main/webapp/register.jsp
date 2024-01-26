<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
    <link rel="stylesheet" type="text/css" href="css/register.css" />
    <!-- Include jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <!-- Include jQuery UI -->
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <!-- Include custom datepicker script -->
    <script src="js/datepicker.js"></script>
</head>
<body onload="showTempAlert(); setMaxBirthdate();">

<div class="container">
    <h2>Register New User</h2>
    <% if (request.getAttribute("successMessage") != null) { %>
    <div id="tempAlert"><%= request.getAttribute("successMessage") %></div>
    <script>showTempAlert();</script>
    <% } %>
    <form id="registrationForm" action="registerUser" method="post" onsubmit="return validateEnglishOnly()">
        <div>
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>
        </div>
        <div>
            <label for="surname">Surname:</label>
            <input type="text" id="surname" name="surname" required>
        </div>
        <div>
            <label for="gender">Gender:</label>
            <select id="gender" name="gender" required>
                <option value="">Select...</option>
                <option value="M">Male</option>
                <option value="F">Female</option>
            </select>
        </div>
        <div>
            <label for="birthdate">Birthdate:</label>
            <!-- Αλλάζουμε τον τύπο του πεδίου σε text για να εμφανίσουμε το datepicker -->
            <input type="text" id="birthdate" name="birthdate" required>
            <!-- Προσθέτουμε πεδίο για εμφάνιση μηνύματος σφάλματος -->
            <div id="birthdate-error" class="error-message" style="display: none;"></div>
        </div>
        <div>
            <label for="workAddress">Work Address:</label>
            <textarea id="workAddress" name="workAddress"></textarea>
        </div>
        <div>
            <label for="homeAddress">Home Address:</label>
            <textarea id="homeAddress" name="homeAddress"></textarea>
        </div>
        <div class="form-buttons">
            <button type="submit" class="btn-register">Register</button>
            <button type="button" class="btn btn-clear" onclick="clearForm()">Clear</button>
        </div>
    </form>
    <div class="nav-buttons">
        <button class="btn btn-nav" onclick="window.location.href='index.jsp'">Home</button>
        <button class="btn btn-nav" onclick="window.location.href='userList.jsp'">View Users</button>
    </div>
</div>
<script src="js/register.js"></script>
</body>
</html>



