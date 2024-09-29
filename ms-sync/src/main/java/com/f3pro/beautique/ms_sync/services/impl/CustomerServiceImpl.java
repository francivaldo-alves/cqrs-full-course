package com.f3pro.beautique.ms_sync.services.impl;

import com.f3pro.beautique.ms_sync.dtos.customers.CustomerDTO;
import com.f3pro.beautique.ms_sync.repositories.CustomerRepository;
import com.f3pro.beautique.ms_sync.services.CustomerService;
import com.f3pro.beautique.ms_sync.utils.SyncLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void saveCustomer(CustomerDTO customer) {
        try{
            SyncLogger.info("Saving customer: " + customer.getId());
            customerRepository.save(customer);
        }catch (Exception e){
            SyncLogger.error("Error saving customer: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }
}