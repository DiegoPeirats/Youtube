package com.example.demo.application;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Order;
import com.example.demo.domain.service.OrderService;
import com.example.demo.infrastructure.client.UserClient;
import com.example.demo.infrastructure.repository.OrderRepository;

import example.order.request.OrderCreationRequest;
import example.order.response.OrderDto;
import example.user.response.AddressDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
	
	
	private final OrderRepository repository;
	
	private final ModelMapper modelMapper;
	
	private final UserClient userClient;
	
	@Override
	public ResponseEntity<OrderDto> insert(OrderCreationRequest request) {
		
		try {
			Order order = Order.builder()
					.userId(request.getUserId())
					.productType(request.getProductType())
					.amount(request.getAmount())
					.price(request.getPrice())
					.build();
			
			repository.save(order);
			
			OrderDto orderDto = modelMapper.map(order, OrderDto.class);
			
			if (request.getAddress() == null) {

				AddressDto address = userClient.getAddress(order.getUserId());
				
				orderDto.setAddress(address);
			}
			
			return ResponseEntity.ok(orderDto);
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@Override
	public ResponseEntity<List<OrderDto>> getOrdersByUser(Long userId) {

		List<OrderDto> ordersFound = repository.findAllByUserId(userId)
				.stream()
				.map(order -> modelMapper.map(order, OrderDto.class))
				.collect(Collectors.toList());
		
		if (ordersFound.isEmpty()) return ResponseEntity.badRequest().build();
		
		return ResponseEntity.ok(ordersFound);
	}

}
