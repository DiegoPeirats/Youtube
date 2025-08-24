package com.example.demo.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.response.UserDto;
import com.example.demo.application.service.UserServiceImpl;
import com.example.demo.infrastructure.request.UserCreationRequest;
import com.example.demo.infrastructure.request.UserUpdateRequest;

import example.user.response.AddressDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
	
	private final UserServiceImpl service;
	
	@PostMapping("/insert")
	public ResponseEntity<UserDto> insertUser(@RequestBody UserCreationRequest request){
		return service.insert(request);
	}

	@PutMapping("/update")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserUpdateRequest request){
		return service.update(request);
	}
	
	@GetMapping("/getUser/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable Long id){
		return service.getUserById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete (@PathVariable Long id){
		return service.delete(id);
	}
	
	@GetMapping("/getAddress/{userId}")
	public AddressDto getAddress (@PathVariable Long userId){
		return service.getAddressByUserId(userId);
	}

}
