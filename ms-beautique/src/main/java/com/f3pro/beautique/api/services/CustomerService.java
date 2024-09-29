package com.f3pro.beautique.api.services;

import com.f3pro.beautique.api.dtos.CustomerDTO;
import com.f3pro.beautique.api.entities.CustomerEntity;

public interface CustomerService {

    CustomerDTO create(CustomerDTO customerDTO);

    void delete(Long id);

    CustomerDTO update(CustomerDTO customerDTO);
}
