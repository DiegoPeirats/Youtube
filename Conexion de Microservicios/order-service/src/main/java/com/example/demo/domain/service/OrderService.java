package com.example.demo.domain.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import example.order.request.OrderCreationRequest;
import example.order.response.OrderDto;

public interface OrderService {
	
	ResponseEntity<OrderDto> insert(OrderCreationRequest request);
	
	ResponseEntity<List<OrderDto>> getOrdersByUser(Long userId);

}
