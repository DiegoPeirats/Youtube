package com.example.demo.infrastructure.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.AddressServiceImpl;

import address.request.AddressCreationRequest;
import address.response.AddressDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AddressController {
	
	private final AddressServiceImpl addressService;
	
	@GetMapping("/getAddress/{userId}")
	public AddressDto getAddressByUserId (@PathVariable Long userId) {
		return addressService.getAddressByUserId(userId);
	}
	
	@PostMapping("/insertAddress")
	public AddressDto insertAddress(@RequestBody AddressCreationRequest request){
		return addressService.insert(request);
	}
	
	@GetMapping ("/addressById/{id}")
	public AddressDto getById (@PathVariable Long id) {
		return addressService.getAddressById(id);
	}

}
