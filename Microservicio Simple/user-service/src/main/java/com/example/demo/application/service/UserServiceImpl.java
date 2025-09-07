package com.example.demo.application.service;

import org.springframework.stereotype.Service;

import com.example.demo.application.response.UserDto;
import com.example.demo.application.response.exception.EmailAlreadyExistsException;
import com.example.demo.application.response.exception.UserNotFoundException;
import com.example.demo.application.utils.UserMapper;
import com.example.demo.domain.entity.User;
import com.example.demo.infrastructure.repository.UserRepository;
import com.example.demo.infrastructure.request.UserCreationRequest;
import com.example.demo.infrastructure.request.UserUpdateRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserRepository repository;

	@Override
	public UserDto create(UserCreationRequest request) {
		checkEmail(request.getEmail());
		
		User user = repository.save(UserMapper.INSTANCE.createUserFromRequest(request));
		
		return UserMapper.INSTANCE.toDto(user);
	}

	@Override
	public UserDto update(UserUpdateRequest request, Long id) {
		
		User user = checkUser(id);
		
		if (!request.getEmail().equalsIgnoreCase(user.getEmail()) && request.getEmail() != null) 
			checkEmail(request.getEmail());
		
		UserMapper.INSTANCE.updateUserFromRequest(request, user);
		
		return UserMapper.INSTANCE.toDto(user);
	}

	@Override
	public UserDto getUser(Long id) {
		User user = checkUser(id);
		return UserMapper.INSTANCE.toDto(user);
	}

	@Override
	public String delete(Long id) {
		checkUser(id);
		repository.deleteById(id);
		return "Eliminado con éxito";
	}
	
	private User checkUser(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException());
	}
	
	private void checkEmail(String email) {
		if (repository.existsByEmail(email)) throw new EmailAlreadyExistsException();
	}

}
