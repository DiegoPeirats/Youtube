package com.example.demo.application;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Product;
import com.example.demo.domain.service.ProductService;
import com.example.demo.infrastructure.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import product.request.ProductCreationRequest;
import product.request.ProductUpdateRequest;
import product.response.ProductDto;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
	
	private final ProductRepository repository;
	
	private final ModelMapper modelMapper;

	@Override
	public ResponseEntity<ProductDto> insert(ProductCreationRequest request) {
		
		try {
			Product product = repository.save(Product.builder()
					.name(request.getName())
					.price(request.getPrice())
					.build());
			
			return ResponseEntity.ok(modelMapper.map(product, ProductDto.class));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}

	@Override
	public ResponseEntity<ProductDto> update(ProductUpdateRequest request) {
		return repository.findById(request.getId())
				.map(product -> {
					product.setName(request.getName() == null? product.getName() : request.getName());
					product.setPrice(request.getPrice() == null? product.getPrice() : request.getPrice());
					
					return ResponseEntity.ok(modelMapper.map(product, ProductDto.class));
				})
				.orElse(ResponseEntity.badRequest().build());
	}

	@Override
	public ProductDto getById(Long id) {
		return repository.findById(id)
				.map(product -> modelMapper.map(product, ProductDto.class))
				.orElse(null);
	}

	@Override
	public String delete(Long id) {
		try {
			repository.deleteById(id);
			return "BORRADO EXITOSO";
		}catch(Exception e) {
			e.printStackTrace();
			return "NO ENCONTRADO";
		}
	}

}
