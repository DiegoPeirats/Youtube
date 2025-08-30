package com.example.demo.application;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entity.Address;
import com.example.demo.domain.service.AddressService;
import com.example.demo.infrastructure.repository.AddressRepository;

import address.request.AddressCreationRequest;
import address.response.AddressDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService{
	
	private final AddressRepository addressRepository;
	
	private final ModelMapper modelMapper;

	@Override
	public AddressDto insert(AddressCreationRequest request) {
		Address address = Address.builder()
				.cp(request.getCp())
				.street(request.getStreet())
				.houseNumber(request.getHouseNumber())
				.userId(request.getUserId())
				.main(getAddressByUserId(request.getUserId()) == null? true : false)
				.build();
		
		addressRepository.save(address);
		return modelMapper.map(address, AddressDto.class);
	}

	@Override
	public AddressDto getAddressByUserId(Long userId) {
		
		return addressRepository.findByUserId(userId)
				.stream()
				.filter(Address::getMain)
				.findFirst()
				.map(address -> modelMapper.map(address, AddressDto.class))
				.orElse(null);
	}
	
	public AddressDto getAddressById(Long id) {
		
		return addressRepository.findById(id)
				.map(address -> modelMapper.map(address, AddressDto.class))
				.orElse(null);
	}
	
	

}