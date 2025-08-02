package com.user.service;

import com.user.model.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO saveEmployee(EmployeeDTO employee);

    EmployeeDTO getEmployee(Long id);

    EmployeeDTO updateEmployee(Long id, EmployeeDTO employee);

    List<EmployeeDTO> getAllEmployees();

    void deleteEmployee(Long id);
}
