package com.example.simplewebapp.model;

/**
 * Represents a work address.
 */
public class WorkAddress {
    private String workAddress;

    /**
     * Constructs a new WorkAddress object with the specified address.
     *
     * @param workAddress The work address as a string.
     */
    public WorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    /**
     * Gets the work address.
     *
     * @return The work address as a string.
     */
    public String getWorkAddress() {
        return workAddress;
    }

    /**
     * Sets the work address.
     *
     * @param workAddress The new work address as a string.
     */
    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }
}




