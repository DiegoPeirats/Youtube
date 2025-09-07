package com.example.demo.application.response.exception;

public class EmailAlreadyExistsException extends RuntimeException{


	private static final long serialVersionUID = 1L;

	public EmailAlreadyExistsException() {
		super("El email ya está en uso");
	}
	
	

}
