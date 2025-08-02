package com.user.client;

import com.user.model.dto.AddressDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "addressClient", url = "${app.address.url}")
public interface AddressClient {

    @GetMapping("/{id}")
    AddressDTO getAddress(@PathVariable Long id);

    @GetMapping("/employee/{empId}")
    AddressDTO getAddressByEmployeeId(@PathVariable Long empId);
}
