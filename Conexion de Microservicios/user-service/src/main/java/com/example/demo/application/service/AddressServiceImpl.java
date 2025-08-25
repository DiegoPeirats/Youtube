package com.example.demo.application.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Address;
import com.example.demo.domain.service.AddressService;
import com.example.demo.infrastructure.repository.AddressRepository;

import lombok.RequiredArgsConstructor;
import user.requests.AddressCreationRequest;
import user.response.AddressDto;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService{
	
	private final AddressRepository addressRepository;
	
	private final ModelMapper modelMapper;

	@Override
	public AddressDto insert(AddressCreationRequest request, Long userId) {
		Address address = Address.builder()
				.cp(request.getCp())
				.street(request.getStreet())
				.houseNumber(request.getHouseNumber())
				.userId(userId == null? request.getUserId() : userId)
				.build();
		
		addressRepository.save(address);
		return modelMapper.map(address, AddressDto.class);
	}

	@Override
	public AddressDto getAddressByUserId(Long userId) {
		
		List<Address> addressList = addressRepository.findByUserId(userId);
		
		if ( !addressList.isEmpty()) return modelMapper.map(addressList.get(0), AddressDto.class);
		return null;
	}
	
	public AddressDto getAddressById(Long id) {
		Optional<Address> address = addressRepository.findById(id);
		
		if (address.isPresent()) return modelMapper.map(address, AddressDto.class);
		
		return null;
	}
	
	

}
