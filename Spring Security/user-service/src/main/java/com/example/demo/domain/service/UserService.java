package com.example.demo.domain.service;

import org.springframework.http.ResponseEntity;

import user.requests.UserUpdateRequest;
import user.response.UserDto;

public interface UserService {

	ResponseEntity<UserDto> update( Long id, UserUpdateRequest request);
	
	ResponseEntity<UserDto> getUserById( Long id);
	
	ResponseEntity<String> delete (Long id);
	
	
}