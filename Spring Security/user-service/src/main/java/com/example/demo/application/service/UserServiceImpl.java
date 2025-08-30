package com.example.demo.application.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.application.response.UserDetailsDto;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.service.UserService;
import com.example.demo.infrastructure.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import user.requests.UserUpdateRequest;
import user.response.UserDto;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserRepository repository;
	
	private final ModelMapper modelMapper;
	

	@Override
	public ResponseEntity<UserDto> update(Long id, UserUpdateRequest request) {
	
		Optional<User> userFound = repository.findById(id);
		
		if (userFound.isPresent()) {
			User user = userFound.get();
			user.setName(request.getName());
			user.setSurname(request.getSurname());
			user.setBirthday(request.getBirthday());
			
			repository.save(user);
			
			return ResponseEntity.ok(modelMapper.map(user, UserDto.class));
		}
		return ResponseEntity.badRequest().build();
	}

	@Override
	public ResponseEntity<UserDto> getUserById(Long id) {
		Optional<User> userFound = repository.findById(id);
		
		if (userFound.isPresent()) {
			User user = userFound.get();
			
			return ResponseEntity.ok(modelMapper.map(user, UserDto.class));
		}
		return ResponseEntity.badRequest().build();
	}

	@Override
	public ResponseEntity<String> delete(Long id) {

		Optional<User> userFound = repository.findById(id);
		
		if (userFound.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.ok("DELETED");
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	public UserDetailsDto idContext() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsDto userDetails = (UserDetailsDto) auth.getPrincipal();
		return userDetails;
	}

}