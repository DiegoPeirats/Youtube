package com.example.demo.application.service;

import com.example.demo.application.response.UserDto;
import com.example.demo.infrastructure.request.UserCreationRequest;
import com.example.demo.infrastructure.request.UserUpdateRequest;

public interface UserService {
	
	UserDto create(UserCreationRequest request);

	UserDto update(UserUpdateRequest request, Long id);
	
	UserDto getUser(Long id);
	
	String delete(Long id);
	

}
