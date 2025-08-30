package com.example.demo.infrastructure.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.OrderServiceImpl;

import lombok.RequiredArgsConstructor;
import order.request.OrderCreationRequest;
import order.response.OrderDto;
import user.response.CustomUserDetails;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderServiceImpl service;
	
	@PostMapping("/insert")
	public ResponseEntity<OrderDto> insert (@RequestBody OrderCreationRequest request){
		return service.insert(request);
	}
	
	@PreAuthorize("hasRole('ADMIN') or #id == principal.id")
	@GetMapping("/getByUser/{id}")
	public ResponseEntity<List<OrderDto>> getOrdersByUser(@PathVariable Long id){
		return service.getOrdersByUser(id);
	}
	
	@GetMapping("/test")
	public CustomUserDetails test() {
		return service.testContext();
	}
}