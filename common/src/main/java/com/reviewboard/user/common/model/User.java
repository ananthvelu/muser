package com.reviewboard.user.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

	@JsonProperty(value = "first_name")
	private String firstName;

	@JsonProperty(value = "last_name")
	private String lastName;

	@JsonProperty(value = "credential")
	private UserCredential userCredential;

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

	public UserCredential getUserCredential() {
		return userCredential;
	}

	public void setUserCredentials(UserCredential userCredential) {
		this.userCredential = userCredential;
	}
}
