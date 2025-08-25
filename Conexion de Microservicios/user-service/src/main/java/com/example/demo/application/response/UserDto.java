package com.example.demo.application.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import user.response.AddressDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

	private Long id;
	
	private String name;
	
	private String surname;
	
	private LocalDate birthday;
	
	private AddressDto address;
}

