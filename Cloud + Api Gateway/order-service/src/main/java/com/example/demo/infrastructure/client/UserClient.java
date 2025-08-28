package com.example.demo.infrastructure.client;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import address.request.AddressCreationRequest;
import address.response.AddressDto;

@Service
public class UserClient {
	
	private WebClient webClient;

	public UserClient(WebClient.Builder builder) {
		this.webClient = builder
							.baseUrl("http://user-service/api/v1")
							.build();
	}
	
	public AddressDto getAddressByUserId(Long userId) {
		
		return webClient.get()
				.uri("/getAddress/{userId}", userId)
				.retrieve()
				.bodyToMono(AddressDto.class)
				.block();
		
	}
	
	public AddressDto insertAddress(AddressCreationRequest request) {
		
		return webClient.post()
				.uri("/insertAddress")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(request)
				.retrieve()
				.bodyToMono(AddressDto.class)
				.block();
		
	}
	
	public AddressDto getById(Long id) {
		
		return webClient.get()
				.uri("/addressById/{id}", id)
				.retrieve()
				.bodyToMono(AddressDto.class)
				.block();
		
	}


}
