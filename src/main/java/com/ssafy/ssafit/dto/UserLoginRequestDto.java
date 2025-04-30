package com.ssafy.ssafit.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class UserLoginRequestDto {

	private String userEmail;
	
	private String userPassword;

	@JsonCreator
	public UserLoginRequestDto(String userEmail, String userPassword) {
		super();
		this.userEmail = userEmail;
		this.userPassword = userPassword;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	
	
	
	
	
}
