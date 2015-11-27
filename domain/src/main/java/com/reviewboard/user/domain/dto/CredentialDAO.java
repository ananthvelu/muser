package com.reviewboard.user.domain.dto;

/**
 * Credentials of the customer
 */
public class CredentialDAO {

    private String username;

    private String email;

    /**
     * Request field not for storing purpose.
     */

    private String password;

    private String passwordHash;

    private String passwordSalt;

    @Override
    public String toString() {
        return String.format(
                "CredentialDAO[username= "+username + " email= " + email +"]");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }
}
