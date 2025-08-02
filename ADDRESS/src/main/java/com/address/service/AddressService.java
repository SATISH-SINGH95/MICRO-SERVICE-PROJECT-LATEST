package com.address.service;

import com.address.model.dto.AddressDTO;
import java.util.List;

public interface AddressService {

    AddressDTO saveAddress(AddressDTO addressDTO);

    AddressDTO updateAddress(Long id, AddressDTO addressDTO);

    AddressDTO getAddress(Long id);

    List<AddressDTO> getAllAddresses();

    void deleteAddress(Long id);

    AddressDTO getAddressByEmployeeId(Long empId);
}
