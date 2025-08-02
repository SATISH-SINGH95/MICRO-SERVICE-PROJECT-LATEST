package com.address.service.impl;

import com.address.exception.BadRequestException;
import com.address.exception.ResourceNotFoundException;
import com.address.model.dto.AddressDTO;
import com.address.model.entity.Address;
import com.address.repository.AddressRepository;
import com.address.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AddressDTO saveAddress(AddressDTO addressDTO) {
        if(addressDTO.getId() != null){
            throw new BadRequestException("Address with id: " + addressDTO.getId() + " already exists", HttpStatus.NOT_FOUND);
        }
        Address addressEntity = modelMapper.map(addressDTO, Address.class);
        Address savedAddress = addressRepository.save(addressEntity);
        return modelMapper.map(savedAddress, AddressDTO.class);
    }

    @Override
    public AddressDTO updateAddress(Long id, AddressDTO addressDTO) {
        if(addressDTO.getId() == null){
            throw new BadRequestException("Address Id required in Request", HttpStatus.BAD_REQUEST);
        }
        addressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address with id: " + id + " not found", HttpStatus.NOT_FOUND));
        Address addressEntity = modelMapper.map(addressDTO, Address.class);
        Address updatedAddress = addressRepository.save(addressEntity);
        return modelMapper.map(updatedAddress, AddressDTO.class);
    }

    @Override
    public AddressDTO getAddress(Long id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address with id: " + id + " not found", HttpStatus.NOT_FOUND));
        return modelMapper.map(address, AddressDTO.class);

    }

    @Override
    public List<AddressDTO> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        if(addresses.isEmpty()){
            throw new ResourceNotFoundException("No addresses found", HttpStatus.NOT_FOUND);
        }
        return addresses.stream().map(address -> modelMapper.map(address, AddressDTO.class)).toList();
    }

    @Override
    public void deleteAddress(Long id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address with id: " + id + " not found", HttpStatus.NOT_FOUND));
        addressRepository.delete(address);
    }

    @Override
    public AddressDTO getAddressByEmployeeId(Long empId) {
        addressRepository.findByEmployeeId(empId).orElseThrow(() -> new ResourceNotFoundException("Address with employee id: " + empId + " not found", HttpStatus.NOT_FOUND));
        return modelMapper.map(addressRepository.findByEmployeeId(empId), AddressDTO.class);
    }
}
