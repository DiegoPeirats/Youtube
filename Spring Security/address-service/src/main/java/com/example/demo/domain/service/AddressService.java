package com.example.demo.domain.service;

import address.request.AddressCreationRequest;
import address.response.AddressDto;

public interface AddressService {
	
	AddressDto insert(AddressCreationRequest request);
	
	AddressDto getAddressByUserId(Long userId);

}