package com.example.demo.application;

import org.springframework.stereotype.Service;

import com.example.demo.application.response.UserDto;
import com.example.demo.application.response.exception.UserAlreadyExistsException;
import com.example.demo.application.response.exception.UserNotFoundException;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.service.UserService;
import com.example.demo.infrastructure.repository.UserRepository;
import com.example.demo.infrastructure.request.UserCreationRequest;
import com.example.demo.infrastructure.request.UserUpdateRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserRepository repository;

	@Override
	public UserDto insert(UserCreationRequest request) {
		
		checkEmail(request.getEmail());
		
		User user = User.builder()
				.name(request.getName())
				.email(request.getEmail())
				.password(request.getPassword())
				.build();
		user = repository.save(user);
		return UserMapper.INSTANCE.toDto(user);
	}

	@Override
	public UserDto update(UserUpdateRequest request) {
		User user = checkUser(request.getId());
		
		if (request.getEmail() != null) checkEmail(request.getEmail());
		
		UserMapper.INSTANCE.updateUserFromRequest(request, user);

		user = repository.save(user);
		return UserMapper.INSTANCE.toDto(user);
	}

	@Override
	public UserDto getById(Long id) {
		User user = checkUser(id);
		
		return UserMapper.INSTANCE.toDto(user);
	}

	@Override
	public String delete(Long id) {
		User user = checkUser(id);
		repository.deleteById(user.getId());
		return "Usuario eliminado";
	}
	
	private void checkEmail(String email) {
		if (repository.existsByEmail(email)) {
			throw new UserAlreadyExistsException("El email ya está en uso");
		}
	}
	
	private User checkUser(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));
	}

}
