package com.f3pro.ms_beautique_query.services;


import com.f3pro.ms_beautique_query.dtos.customers.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> listAllCustomers();
    List<CustomerDTO> listByNameLikeIgnoreCase(String name);
    List<CustomerDTO> listByEmailLikeIgnoreCase(String email);

}
