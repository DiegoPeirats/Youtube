package com.example.demo.application.response.exception;

public class UserNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
		super("Usuario no encontrado");
	}
	
	

}
