package com.reviewboard.user.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User Login credentials
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCredential {
    @JsonProperty(value = "user_name")
    private String username;

    @JsonProperty(value = "password")
    private String password;

    @JsonProperty(value = "email")
    private String email;

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

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }
}
