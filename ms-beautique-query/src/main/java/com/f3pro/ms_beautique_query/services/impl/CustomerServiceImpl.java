package com.f3pro.ms_beautique_query.services.impl;

import com.f3pro.ms_beautique_query.dtos.customers.CustomerDTO;
import com.f3pro.ms_beautique_query.repositories.CustomerRepository;
import com.f3pro.ms_beautique_query.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> listAllCustomers() {
        try {
            return customerRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error listing all customers");
        }
    }

    @Override
    public List<CustomerDTO> listByNameLikeIgnoreCase(String name) {
        try {
            return customerRepository.findByNameLikeIgnoreCase(name);
        } catch (Exception e) {
            throw new RuntimeException("Error listing all customers by name");
        }
    }

    @Override
    public List<CustomerDTO> listByEmailLikeIgnoreCase(String email) {
        try {
            return customerRepository.findByEmailLikeIgnoreCase(email);
        } catch (Exception e) {
            throw new RuntimeException("Error listing all customers by email");
        }
    }
}
