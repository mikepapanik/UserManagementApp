package com.example.simplewebapp.model;

/**
 * Represents a User entity with personal information.
 */
public class User {
    private int id;
    private String name;
    private String surname;
    private char gender;
    private java.sql.Date birthdate;
    private HomeAddress homeAddress;
    private WorkAddress workAddress;

    /**
     * Default constructor for User class.
     */
    public User() {}

    /**
     * Constructs a new User object with the specified attributes.
     *
     * @param id        The unique identifier of the user.
     * @param name      The user's first name.
     * @param surname   The user's last name.
     * @param gender    The user's gender ('M' for male, 'F' for female).
     * @param birthdate The user's birthdate as a SQL Date object.
     */
    public User(int id, String name, String surname, char gender, java.sql.Date birthdate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthdate = birthdate;
    }

    /**
     * Gets the user's home address.
     *
     * @return The user's home address as a HomeAddress object.
     */
    public HomeAddress getHomeAddress() {
        return homeAddress;
    }

    /**
     * Sets the user's home address.
     *
     * @param homeAddress The user's home address as a HomeAddress object.
     */
    public void setHomeAddress(HomeAddress homeAddress) {
        this.homeAddress = homeAddress;
    }

    /**
     * Gets the user's work address.
     *
     * @return The user's work address as a WorkAddress object.
     */
    public WorkAddress getWorkAddress() {
        return workAddress;
    }

    /**
     * Sets the user's work address.
     *
     * @param workAddress The user's work address as a WorkAddress object.
     */
    public void setWorkAddress(WorkAddress workAddress) {
        this.workAddress = workAddress;
    }

    // Optional: Override toString(), equals(), and hashCode() based on your requirements
}






