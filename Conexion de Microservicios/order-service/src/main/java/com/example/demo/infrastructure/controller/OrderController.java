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

import example.order.request.OrderCreationRequest;
import example.order.response.OrderDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderServiceImpl service;
	
	@PostMapping("/insert")
	public ResponseEntity<OrderDto> insert (@RequestBody OrderCreationRequest request){
		return service.insert(request);
	}
	
	@GetMapping("/getOrders/{id}")
	public ResponseEntity<List<OrderDto>> getOrders(@PathVariable Long id){
		return service.getOrdersByUser(id);
	}

}
