package com.example.demo.application;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Order;
import com.example.demo.domain.service.OrderService;
import com.example.demo.infrastructure.client.UserClient;
import com.example.demo.infrastructure.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import order.request.OrderCreationRequest;
import order.response.OrderDto;
import user.response.AddressDto;

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
					.product(request.getProduct())
					.amount(request.getAmount())
					.build();
			
			OrderDto orderDto = modelMapper.map(order, OrderDto.class);
			AddressDto addressDto = null;
			
			if (request.getAddress() == null) {
				addressDto = userClient.getAddressByUserId(request.getUserId());
				
				
			}else {
				request.getAddress().setUserId(request.getUserId());
				
				addressDto = userClient.insertAddress(request.getAddress());
				
			}
			
			orderDto.setAddress(addressDto);
			order.setAddressId(addressDto.getId());
			
			repository.save(order);
			
			orderDto.setPrice(order.getPrice());
			
			return ResponseEntity.ok(orderDto);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}

	@Override
	public ResponseEntity<List<OrderDto>> getOrdersByUser(Long userId) {
		List<OrderDto> orderList = repository.findAllByUserId(userId)
				.stream()
				.map(order -> {
					OrderDto orderDto = modelMapper.map(order, OrderDto.class);
					orderDto.setAddress(userClient.getById(order.getAddressId()));
					return orderDto;
					
				})
				.toList();
		
		if (orderList.isEmpty()) return ResponseEntity.badRequest().build();
		
		return ResponseEntity.ok(orderList);
	}

}
