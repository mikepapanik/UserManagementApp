package com.example.simplewebapp.model;

/**
 * Represents a home address.
 */
public class HomeAddress {
    private String homeAddress;

    /**
     * Constructs a new HomeAddress object with the specified address.
     *
     * @param homeAddress The home address as a string.
     */
    public HomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    /**
     * Gets the home address.
     *
     * @return The home address as a string.
     */
    public String getHomeAddress() {
        return homeAddress;
    }

    /**
     * Sets the home address.
     *
     * @param homeAddress The new home address as a string.
     */
    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }
}


