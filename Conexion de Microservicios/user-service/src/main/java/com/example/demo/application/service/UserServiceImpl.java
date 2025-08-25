package com.example.demo.application.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.application.response.UserDto;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.service.UserService;
import com.example.demo.infrastructure.repository.UserRepository;
import com.example.demo.infrastructure.request.UserCreationRequest;
import com.example.demo.infrastructure.request.UserUpdateRequest;

import lombok.RequiredArgsConstructor;
import user.response.AddressDto;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserRepository repository;
	
	private final ModelMapper modelMapper;
	
	private final AddressServiceImpl addressService;
	

	@Override
	public ResponseEntity<UserDto> insert(UserCreationRequest request) {
		
		try {
			User user = User.builder()
					.name(request.getName())
					.surname(request.getSurname())
					.birthday(request.getBirthday())
					.build();
			
			repository.save(user);
			
			AddressDto address = addressService.insert(request.getAddress(), user.getId());
			
			UserDto userDto = modelMapper.map(user, UserDto.class);
			
			userDto.setAddress(address);
			
			return ResponseEntity.ok(userDto);
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@Override
	public ResponseEntity<UserDto> update(UserUpdateRequest request) {
		Optional<User> userFound = repository.findById(request.getId());
		
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

}
