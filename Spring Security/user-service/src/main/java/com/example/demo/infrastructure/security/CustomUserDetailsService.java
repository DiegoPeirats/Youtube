package com.example.demo.infrastructure.security;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.application.response.UserDetailsDto;
import com.example.demo.domain.entity.User;
import com.example.demo.infrastructure.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	
	private final UserRepository repository;
	
	private final ModelMapper modelMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = repository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException(username + "not found"));
		
		return modelMapper.map(user, UserDetailsDto.class);
	}
	
	

}
