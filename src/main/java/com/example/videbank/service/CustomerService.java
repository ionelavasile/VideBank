package com.example.videbank.service;
import com.example.videbank.dto.CustomerDto;
import java.util.List;

public interface CustomerService {

    CustomerDto getById(Long id);

    List<CustomerDto> getAllCustomers();

    CustomerDto createCustomer(CustomerDto customerDto);

    CustomerDto updateCustomer(CustomerDto customerDto);

    void deleteCustomer(Long id);

    CustomerDto saveCustomer(CustomerDto customerDto);
}
