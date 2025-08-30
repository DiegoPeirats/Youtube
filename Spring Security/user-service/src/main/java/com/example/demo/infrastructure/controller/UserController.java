package com.example.demo.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.response.UserDetailsDto;
import com.example.demo.application.service.LoginService;
import com.example.demo.application.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;
import user.requests.LoginRequest;
import user.requests.UserCreationRequest;
import user.requests.UserUpdateRequest;
import user.response.LoginDto;
import user.response.UserDto;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl service;
    private final LoginService loginService;

    @PostMapping("/public/create")
    public ResponseEntity<?> insertUser(@RequestBody UserCreationRequest request){
        return loginService.createUser(request);
    }

    @PostMapping("/public/login")
    public ResponseEntity<LoginDto> login(@RequestBody LoginRequest request){
        return loginService.login(request);
    }

    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request){
        return service.update(id, request);
    }

    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        return service.getUserById(id);
    }

    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return service.delete(id);
    }
    
    @GetMapping("/testContext")
    public UserDetailsDto test() {
    	return service.idContext();
    }
}

