# UserManagementApp

A simple Java-based web application for user registration and management.

## Description

This project is a basic web application that allows users to register, view, and manage user information. It demonstrates the use of servlets, JSP, and a MySQL database for storing user data.

## Features

- User registration
- User login
- Display user information
- Manage user data

## Technologies Used

- Java
- Java Servlets
- JSP (JavaServer Pages)
- MySQL
- JDBC
- Maven
- Tomcat

## Getting Started

### Prerequisites

- JDK 11 or higher
- Maven
- MySQL
- Tomcat 9

### Installation

1. **Clone the repository**:
    ```sh
    git clone https://github.com/yourusername/UserManagementApp.git
    cd UserManagementApp
    ```

2. **Set up the MySQL database**:
    - Create a new database named `usermanagement`.
    - Import the SQL file located at `src/main/resources/database.sql`.

3. **Configure the database connection**:
    - Open `src/main/resources/db.properties` and update the database connection details.

4. **Build the project**:
    ```sh
    mvn clean install
    ```

5. **Deploy to Tomcat**:
    - Copy the generated WAR file from `target/UserManagementApp-1.0-SNAPSHOT.war` to the `webapps` directory of your Tomcat installation.
    - Start the Tomcat server.

6. **Access the application**:
    - Open your browser and go to `http://localhost:8080/UserManagementApp`.

## Usage

- Navigate to the registration page to create a new user.
- Use the login page to access the application.
- View and manage user information through the user dashboard.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
