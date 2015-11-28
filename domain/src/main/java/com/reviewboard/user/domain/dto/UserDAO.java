package com.reviewboard.user.domain.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * User data access objects.
 */
@Document(collection = "user")
public class UserDAO {

    @Id
    private String id;

    @Field("firstName")
    private String firstName;

    @Field("lastName")
    private String lastName;

    @Field("credential")
    private CredentialDAO credentialDAO;

    @Override
    public String toString() {
        return String.format(
                "Customer[firstName=%s, lastName='%s', userName='%s']",
                firstName, lastName, credentialDAO.getUsername());
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

    public CredentialDAO getCredentialDAO() { return credentialDAO; }
}

