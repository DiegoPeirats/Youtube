package com.example.demo.infrastructure.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.response.UserDto;
import com.example.demo.application.service.UserService;
import com.example.demo.infrastructure.request.UserCreationRequest;
import com.example.demo.infrastructure.request.UserUpdateRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	
	private final UserService service;
	
	@PostMapping("/create")
	public ResponseEntity<UserDto> create(@RequestBody UserCreationRequest request){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<UserDto> update(@RequestBody UserUpdateRequest request, @PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(service.update(request, id));
	}	
	
	@GetMapping("/getUser/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.FOUND).body(service.getUser(id));
	}	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
	}
}
