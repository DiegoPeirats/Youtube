package com.example.demo.infrastructure.request;

import lombok.Getter;

@Getter
public class UserUpdateRequest {
	
	private Long id;
	
	private String name;
	
	private String email;
	
	private String password;

}
