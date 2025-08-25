package com.example.demo.infrastructure.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderServiceImpl service;
	
	@PostMapping("/insert")
	public ResponseEntity<OrderDto> insert (@RequestBody OrderCreationRequest request){
		return service.insert(request);
	}
	
	@GetMapping("/getByUser/{userId}")
	public ResponseEntity<List<OrderDto>> getOrdersByUser(@PathVariable Long userId){
		return service.getOrdersByUser(userId);
	}
}
