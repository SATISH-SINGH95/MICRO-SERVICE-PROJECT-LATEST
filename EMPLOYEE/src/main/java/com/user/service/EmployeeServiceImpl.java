package com.user.service;

import com.user.client.AddressClient;
import com.user.exception.BadRequestException;
import com.user.exception.ResourceNotFoundException;
import com.user.model.dto.AddressDTO;
import com.user.model.dto.EmployeeDTO;
import com.user.model.entity.Employee;
import com.user.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final AddressClient addressClient;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                                ModelMapper modelMapper,
                                AddressClient addressClient) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.addressClient = addressClient;
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        if(employeeDTO.getId() != null) {
            throw new BadRequestException("Employee id is required", HttpStatus.BAD_REQUEST);
        }
        Employee employeeEntity = modelMapper.map(employeeDTO, Employee.class);
        Employee savedEmployee = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO getEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found", HttpStatus.NOT_FOUND));
        AddressDTO address = addressClient.getAddressByEmployeeId(id);
        EmployeeDTO empDTO = modelMapper.map(employee, EmployeeDTO.class);
        empDTO.setAddress(address);
        return empDTO;
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        if(employeeDTO.getId() == null){
            throw new BadRequestException("Please enter employee id", HttpStatus.BAD_REQUEST);
        }
        employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found", HttpStatus.NOT_FOUND));
        Employee employeeEntity = modelMapper.map(employeeDTO, Employee.class);
        Employee updatedEmployee = employeeRepository.save(employeeEntity);
        return modelMapper.map(updatedEmployee, EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        if(employees.isEmpty()){
            throw new ResourceNotFoundException("No employees found", HttpStatus.NOT_FOUND);
        }

        List<EmployeeDTO> empList = employees.stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class)).toList();
        List<EmployeeDTO> response = new ArrayList<>();
        for(EmployeeDTO employee : empList) {
            AddressDTO address = null;
            try{
                address = addressClient.getAddressByEmployeeId(employee.getId());
            }catch (Exception e){
                log.error("Address not found for employee {}", employee.getId());
            }
            employee.setAddress(address);
            response.add(employee);
        }
        return response;
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found", HttpStatus.NOT_FOUND));
        employeeRepository.deleteById(id);
    }
}
