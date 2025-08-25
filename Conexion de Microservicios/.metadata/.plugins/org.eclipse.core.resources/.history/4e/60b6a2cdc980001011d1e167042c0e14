package com.example.demo.infrastructure.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import example.order.request.OrderCreationRequest;
import example.order.response.OrderDto;

@FeignClient(name="order-service", url = "http://localhost:8081/api/v1/orders")
public interface OrderClient {
	
	@PostMapping("/insert")
	ResponseEntity<OrderDto> insert (@RequestBody OrderCreationRequest request);
	
	@GetMapping("/getOrders/{id}")
	ResponseEntity<List<OrderDto>> getOrders(@PathVariable Long id);

}
