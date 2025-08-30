package com.example.demo.application.service;

import java.net.URI;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Role;
import com.example.demo.domain.entity.User;
import com.example.demo.infrastructure.client.AddressClient;
import com.example.demo.infrastructure.repository.UserRepository;
import com.example.demo.infrastructure.security.JwtTokenProvider;

import address.response.AddressDto;
import lombok.RequiredArgsConstructor;
import user.requests.LoginRequest;
import user.requests.UserCreationRequest;
import user.response.LoginDto;
import user.response.UserDto;

@Service
@RequiredArgsConstructor
public class LoginService {
	
	private final UserRepository repository;
	
	private final ModelMapper modelMapper;
	
	private final AddressClient addressClient;
	
	private final PasswordEncoder passwordEncoder;
	
	private final AuthenticationManager authenticationManager;
	
	private final JwtTokenProvider jwtTokenProvider;
	
	
	
	public ResponseEntity<?> createUser(UserCreationRequest request){
		
		try {
			if (repository.existsByEmail(request.getEmail())) return ResponseEntity.badRequest().build();
			
			User user = User.builder()
					.name(request.getName())
					.surname(request.getSurname())
					.birthday(request.getBirthday())
					.email(request.getEmail())
					.password(passwordEncoder.encode(request.getPassword()))
					.role(Role.ROLE_USER)
					.build();
			
			repository.save(user);
			
			request.getAddress().setUserId(user.getId());
			
			AddressDto address = addressClient.insert(request.getAddress());
			
			UserDto userDto = modelMapper.map(user, UserDto.class);
			
			userDto.setAddress(address);
	        URI location = URI.create("/users/" + user.getId());

	        return ResponseEntity.created(location).body(userDto);
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}

	public ResponseEntity<LoginDto> login(LoginRequest request) {
		try {
			Authentication authenticate = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
			
			User user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new IllegalArgumentException());
			UserDto userDto = modelMapper.map(user, UserDto.class);
			userDto.setAddress(addressClient.getAddressByUserId(user.getId()));
			return ResponseEntity.ok(new LoginDto(userDto, jwtTokenProvider.generateToken(authenticate)));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}

}
