package com.example.demo.application;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Order;
import com.example.demo.domain.service.OrderService;
import com.example.demo.infrastructure.client.AddressClient;
import com.example.demo.infrastructure.client.ProductClient;
import com.example.demo.infrastructure.repository.OrderRepository;

import address.response.AddressDto;
import lombok.RequiredArgsConstructor;
import order.request.OrderCreationRequest;
import order.response.OrderDto;
import product.response.ProductDto;
import user.response.CustomUserDetails;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
	
	private final OrderRepository repository;
	
	private final ModelMapper modelMapper;
	
	private final AddressClient addressClient;
	
	private final ProductClient productClient;
	
	@Override
	public ResponseEntity<OrderDto> insert(OrderCreationRequest request) {
		try {
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
			
			ProductDto productDto= productClient.getById(request.getProductId());
			Order order = Order.builder()
					.userId(user.getId())
					.productId(request.getProductId())
					.amount(request.getAmount())
					.price(productDto.getPrice() * request.getAmount())
					.build();
			
			OrderDto orderDto = modelMapper.map(order, OrderDto.class);
			orderDto.setProduct(productDto.getName());
			AddressDto addressDto = null;
			
			if (request.getAddress() == null) {
				addressDto = addressClient.getAddressByUserId(user.getId());
				
				
			}else {
				request.getAddress().setUserId(user.getId());
				
				addressDto = addressClient.insertAddress(request.getAddress());
				
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
					orderDto.setAddress(addressClient.getById(order.getAddressId()));
					orderDto.setProduct(productClient.getById(order.getProductId()).getName());
					return orderDto;
					
				})
				.toList();
		
		if (orderList.isEmpty()) return ResponseEntity.ok(List.of());
		
		return ResponseEntity.ok(orderList);
	}
	
	public CustomUserDetails testContext() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return (CustomUserDetails) auth.getPrincipal();
	}

}