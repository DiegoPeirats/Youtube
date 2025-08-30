package com.example.demo.domain.service;

import org.springframework.http.ResponseEntity;

import product.request.ProductCreationRequest;
import product.request.ProductUpdateRequest;
import product.response.ProductDto;

public interface ProductService {
	
	ResponseEntity<ProductDto> insert (ProductCreationRequest request);
	
	ResponseEntity<ProductDto> update (ProductUpdateRequest request);
	
	ProductDto getById (Long id);
	
	String delete (Long id);

}
