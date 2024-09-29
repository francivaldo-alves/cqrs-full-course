package com.f3pro.beautique.ms_sync.repositories;

import com.f3pro.beautique.ms_sync.dtos.customers.CustomerDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerDTO, Long> {
}
