package com.example.demo.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.ProductServiceImpl;

import lombok.RequiredArgsConstructor;
import product.request.ProductCreationRequest;
import product.request.ProductUpdateRequest;
import product.response.ProductDto;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductServiceImpl service;
	
	@PostMapping("/insert")
	public ResponseEntity<ProductDto> insert (@RequestBody ProductCreationRequest request) {
		return service.insert(request);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ProductDto> update (@RequestBody ProductUpdateRequest request){
		return service.update(request);
	}
	
	@DeleteMapping("/delete")
	public String delete (Long id) {
		return service.delete(id);
	}
	
	@GetMapping("/getById")
	public ProductDto getById(Long id) {
		return service.getById(id);
	}

}
