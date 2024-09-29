package com.f3pro.beautique.api.services.impl;

import com.f3pro.beautique.api.dtos.CustomerDTO;
import com.f3pro.beautique.api.entities.CustomerEntity;
import com.f3pro.beautique.api.repositories.CustomerRepository;
import com.f3pro.beautique.api.services.BrokerService;
import com.f3pro.beautique.api.services.CustomerService;
import com.f3pro.beautique.api.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private BrokerService brokerService;

    @Autowired
    private CustomerRepository customerRepository;

    // Utilitário para conversão entre entidade e DTO
    private final ConverterUtil<CustomerEntity, CustomerDTO> converterUtil
            = new ConverterUtil<>(CustomerEntity.class, CustomerDTO.class);

    // Cria um novo cliente e o envia para a fila
    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = convertDtoToEntity(customerDTO);
        CustomerEntity savedCustomer = customerRepository.save(customerEntity);
        sendCustomerToQueue(savedCustomer);
        return convertEntityToDto(savedCustomer);
    }

    // Deleta um cliente existente pelo ID
    @Override
    public void delete(Long id) {
        CustomerEntity customerEntity = findCustomerById(id);
        customerRepository.delete(customerEntity);
    }

    // Atualiza um cliente existente e o envia para a fila
    @Override
    public CustomerDTO update(CustomerDTO customerDTO) {
        CustomerEntity existingCustomer = findCustomerById(customerDTO.getId());
        CustomerEntity updatedCustomer = convertDtoToEntity(customerDTO);
        preserveExistingData(existingCustomer, updatedCustomer);
        CustomerEntity savedCustomer = customerRepository.save(updatedCustomer);
        sendCustomerToQueue(savedCustomer);
        return convertEntityToDto(savedCustomer);
    }

    // Envia o cliente para a fila via BrokerService
    private void sendCustomerToQueue(CustomerEntity customerEntity) {
        CustomerDTO customerDTO = convertEntityToDto(customerEntity);
        brokerService.send("customer", customerDTO);
    }

    // Converte um DTO para uma entidade
    private CustomerEntity convertDtoToEntity(CustomerDTO customerDTO) {
        return converterUtil.convertToSource(customerDTO);
    }

    // Converte uma entidade para um DTO
    private CustomerDTO convertEntityToDto(CustomerEntity customerEntity) {
        return converterUtil.convertToTarget(customerEntity);
    }

    // Preserva dados existentes durante a atualização
    private void preserveExistingData(CustomerEntity existingCustomer, CustomerEntity updatedCustomer) {
        updatedCustomer.setAppointments(existingCustomer.getAppointments());
        updatedCustomer.setCreatedAt(existingCustomer.getCreatedAt());
    }

    // Busca um cliente pelo ID ou lança exceção se não encontrado
    private CustomerEntity findCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id));
    }
}
