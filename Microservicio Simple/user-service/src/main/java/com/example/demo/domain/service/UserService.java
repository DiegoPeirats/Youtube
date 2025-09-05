package com.example.demo.domain.service;


import com.example.demo.application.response.UserDto;
import com.example.demo.infrastructure.request.UserCreationRequest;
import com.example.demo.infrastructure.request.UserUpdateRequest;

public interface UserService {
	
	UserDto insert(UserCreationRequest request);
	
	UserDto update(UserUpdateRequest request);
	
	UserDto getById(Long id);
	
	String delete (Long id);

}
