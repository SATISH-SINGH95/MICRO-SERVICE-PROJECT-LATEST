package com.user.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDTO {

    private Long id;

    @Size(min = 3, max = 20)
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotNull(message = "Employee code is required")
    private Long empCode;

    private AddressDTO address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getEmpCode() {
        return empCode;
    }

    public void setEmpCode(Long empCode) {
        this.empCode = empCode;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public EmployeeDTO(Long id, String name, String companyName, Long empCode) {
        this.id = id;
        this.name = name;
        this.companyName = companyName;
        this.empCode = empCode;
    }

    public EmployeeDTO() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", companyName='" + companyName + '\'' +
                ", empCode=" + empCode +
                '}';
    }
}
