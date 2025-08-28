package com.example.demo.domain.service;

import org.springframework.http.ResponseEntity;


import user.requests.UserCreationRequest;
import user.requests.UserUpdateRequest;
import user.response.UserDto;

public interface UserService {
	
	ResponseEntity<UserDto> insert( UserCreationRequest request);

	ResponseEntity<UserDto> update( UserUpdateRequest request);
	
	ResponseEntity<UserDto> getUserById( Long id);
	
	ResponseEntity<String> delete (Long id);
	
	
}