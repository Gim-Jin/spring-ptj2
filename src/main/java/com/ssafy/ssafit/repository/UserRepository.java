package com.ssafy.ssafit.repository;

import java.util.List;

import com.ssafy.ssafit.dto.UserDto;

public interface UserRepository {
	
	public int insert(UserDto user);
	
	public UserDto selectByEmail(String email);
	
	public int deleteUserByEmail(String email);
	
	public List<UserDto> selectAll();
	
	public int update(UserDto user);
	
}
