package com.ssafy.ssafit.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.ssafit.dto.UserDto;
import com.ssafy.ssafit.exception.InvalidUserFormatException;
import com.ssafy.ssafit.exception.UserNotFoundException;
import com.ssafy.ssafit.repository.UserRepository;
import com.ssafy.ssafit.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		
		this.userRepository = userRepository;
		
	}

	@Override
	@Transactional
	public boolean registUser(UserDto user) {
		
		if(user == null) {
			throw new InvalidUserFormatException();
		}
		
		return userRepository.insert(user) == 1 ? true : false;
		
	}

	@Override
	@Transactional
	public boolean withdrawUser(String userEmail) {
		
		if(userEmail == null || userEmail.equals("")) {
			throw new InvalidUserFormatException();
		}
		
		return userRepository.deleteUserByEmail(userEmail) == 1 ? true : false;
		
	}

	@Override
	@Transactional
	public boolean modifyUser(UserDto user) {
		
		if(user == null) {
			throw new InvalidUserFormatException();
		}
		
		return userRepository.update(user) == 1 ? true : false;
		
		
	}

	@Override
	public List<UserDto> getAllUser() {
		
		return userRepository.selectAll();
		
	}

	@Override
	public UserDto getUserByEmail(String userEmail) {
		
		if(userEmail == null || userEmail.equals("")) {
			throw new InvalidUserFormatException();
		}
		
		UserDto result = userRepository.selectByEmail(userEmail);
		
		if(result == null) {
			
			throw new UserNotFoundException();
			
		}
			
		return result;
		
	}

	@Override
	public UserDto login(String userEmail, String userPassword) {
		
		UserDto result = userRepository.selectByEmail(userEmail);
		
		if(result != null && result.getUserPassword().equals(userPassword)) {
			
			return result;
		}
		
		throw new UserNotFoundException();
		
	}
	
	

}
