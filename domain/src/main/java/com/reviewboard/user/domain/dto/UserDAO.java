package com.reviewboard.user.domain.dto;

/**
 * User data access objects.
 */
public class UserDAO {
    private String id;
    private String firstName;

    private String lastName;

    private CredentialDAO credentialDAO;

    @Override
    public String toString() {
        return String.format(
                "Customer[firstName=%s, lastName='%s', userName='%s']",
                firstName, lastName, credentialDAO);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCredentialDAO(CredentialDAO credentialDAO) {
        this.credentialDAO = credentialDAO;
    }
    public CredentialDAO getCredentialDAO () { return credentialDAO; }
}

