package com.example.demo.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import user.requests.AddressCreationRequest;
import user.response.AddressDto;

@FeignClient(name = "user-service" , url = "http://localhost:8080/api/v1/users")
public interface UserClient {
	
	@GetMapping("/getAddress/{userId}")
	AddressDto getAddressByUserId (@PathVariable Long userId);
	

	
	@PostMapping("/insertAddress")
	AddressDto insertAddress(@RequestBody AddressCreationRequest request);
	
	@GetMapping ("/addressById/{id}")
	AddressDto getById (@PathVariable Long id);

}
