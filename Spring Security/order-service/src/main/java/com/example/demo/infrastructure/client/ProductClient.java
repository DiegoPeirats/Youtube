package com.example.demo.infrastructure.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import product.response.ProductDto;

@Service
public class ProductClient {
	
	private WebClient webClient;

	public ProductClient(WebClient.Builder builder) {
		this.webClient = builder
							.baseUrl("http://product-service/api/v1")
							.build();
	}
	
	public ProductDto getById(Long id) {
		return webClient.get()
				.uri("/getById")
				.retrieve()
				.bodyToMono(ProductDto.class)
				.block();
	}

}
