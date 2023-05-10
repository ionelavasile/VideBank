package com.example.videbank.service.impl;

import com.example.videbank.dto.CustomerDto;
import com.example.videbank.entity.Customer;
import com.example.videbank.mapper.CustomerMapper;
import com.example.videbank.repository.CustomerRepository;
import com.example.videbank.service.CustomerService;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDto getById(Long id) {
        Customer customer = customerRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
        return customerMapper.toDto(customer);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto).toEntity();
        customer = customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto).toEntity();
        customer = customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        Customer customer;
        customer = customerMapper.toEntity(customerDto).toEntity();
        customer = customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
        customerRepository.delete(customer);
    }

}

