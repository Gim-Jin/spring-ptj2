package com.ssafy.ssafit.service;

import java.util.List;

import com.ssafy.ssafit.dto.UserDto;

public interface UserService {
	
	public boolean registUser(UserDto user);
	
	public boolean withdrawUser(String userEmail);
	
	public boolean modifyUser(UserDto user);
	
	public List<UserDto> getAllUser();
	
	public UserDto getUserByEmail(String userEmail);
	
	public UserDto login(String userEmail, String userPassword);
	
}
