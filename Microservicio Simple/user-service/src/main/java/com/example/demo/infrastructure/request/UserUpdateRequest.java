package com.example.demo.infrastructure.request;

import lombok.Getter;

@Getter
public class UserUpdateRequest {
	
	private String name;
	
	private String email;
	
	private String password;

}
