package com.address.controller;

import com.address.model.dto.AddressDTO;
import com.address.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/save")
    public ResponseEntity<AddressDTO> saveAddress(@RequestBody AddressDTO addressDTO) {
        return new ResponseEntity<>(addressService.saveAddress(addressDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddress(@PathVariable Long id) {
        return new ResponseEntity<>(addressService.getAddress(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAllAddresses() {
        return new ResponseEntity<>(addressService.getAllAddresses(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        return new ResponseEntity<>(addressService.updateAddress(id, addressDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/employee/{empId}")
    public ResponseEntity<AddressDTO> getAddressByEmployeeId(@PathVariable Long empId) {
        return new ResponseEntity<>(addressService.getAddressByEmployeeId(empId), HttpStatus.OK);
    }
}
