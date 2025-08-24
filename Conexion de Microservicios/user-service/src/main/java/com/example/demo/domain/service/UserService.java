package com.example.demo.domain.service;

import org.springframework.http.ResponseEntity;

import com.example.demo.application.response.UserDto;
import com.example.demo.infrastructure.request.UserCreationRequest;
import com.example.demo.infrastructure.request.UserUpdateRequest;

public interface UserService {
	
	ResponseEntity<UserDto> insert( UserCreationRequest request);

	ResponseEntity<UserDto> update( UserUpdateRequest request);
	
	ResponseEntity<UserDto> getUserById( Long id);
	
	ResponseEntity<String> delete (Long id);
	
	
}
