package com.f3pro.beautique.api.repositories;

import com.f3pro.beautique.api.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
}
