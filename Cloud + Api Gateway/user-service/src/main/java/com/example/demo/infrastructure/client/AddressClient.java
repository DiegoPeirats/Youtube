package com.example.demo.infrastructure.client;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import address.request.AddressCreationRequest;
import address.response.AddressDto;


@Service
public class AddressClient {
	
	private WebClient webClient;

	public AddressClient(WebClient.Builder builder) {
		this.webClient = builder
							.baseUrl("http://address-service")
							.build();
	}
	
	public AddressDto insert(AddressCreationRequest request) {
		return webClient.post()
				.uri("/api/v1/insertAddress")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(request)
				.retrieve()
				.bodyToMono(AddressDto.class)
				.block();
				
	}

}
