package com.f3pro.beautique.api.controllers;


import com.f3pro.beautique.api.dtos.CustomerDTO;
import com.f3pro.beautique.api.entities.CustomerEntity;
import com.f3pro.beautique.api.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

/**
 * Controlador para gerenciar operações relacionadas a clientes.
 */
@RestController
@RequestMapping("/customers")
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Cria um novo cliente.
     *
     * @param customerDTO o DTO do cliente a ser criado.
     * @return a resposta com o DTO do cliente criado.
     */
    @PostMapping
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO) {
        log.info("Criando cliente: {}", customerDTO);
        CustomerDTO createdCustomer = customerService.create(customerDTO);
        return ResponseEntity.ok(createdCustomer);
    }

    /**
     * Deleta um cliente pelo ID.
     *
     * @param id o ID do cliente a ser deletado.
     * @return a resposta sem corpo (204 No Content).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Deletando cliente com ID: {}", id);
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Atualiza um cliente existente.
     *
     * @param customerDTO o DTO do cliente a ser atualizado.
     * @return a resposta com o DTO do cliente atualizado.
     */
    @PatchMapping
    public ResponseEntity<CustomerDTO> update(@RequestBody CustomerDTO customerDTO) {
        log.info("Atualizando cliente: {}", customerDTO);
        CustomerDTO updatedCustomer = customerService.update(customerDTO);
        return ResponseEntity.ok(updatedCustomer);
    }
}
