package com.example.demo.infrastructure.request;

import java.time.LocalDate;

import lombok.Getter;
import user.requests.AddressCreationRequest;

@Getter
public class UserCreationRequest {

	private String name;
	
	private String surname;
	
	private LocalDate birthday;
	
	private AddressCreationRequest address;

}
