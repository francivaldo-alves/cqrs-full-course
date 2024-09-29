package com.f3pro.beautique.api.services.impl;

import com.f3pro.beautique.api.dtos.AppointmentDTO;
import com.f3pro.beautique.api.dtos.BeautyProcedureDTO;
import com.f3pro.beautique.api.dtos.CustomerDTO;
import com.f3pro.beautique.api.dtos.FullAppointmentDTO;
import com.f3pro.beautique.api.entities.AppointmentsEntity;
import com.f3pro.beautique.api.entities.BeautyProceduresEntity;
import com.f3pro.beautique.api.entities.CustomerEntity;
import com.f3pro.beautique.api.repositories.AppointmentRepository;
import com.f3pro.beautique.api.repositories.BeautyProcedureRepository;
import com.f3pro.beautique.api.repositories.CustomerRepository;
import com.f3pro.beautique.api.services.AppointmentService;
import com.f3pro.beautique.api.services.BrokerService;
import com.f3pro.beautique.api.utils.ConverterUtil;
import jdk.jfr.Unsigned;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private BrokerService brokerService;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // Utilitário para conversão entre entidades e DTOs
    private final ConverterUtil<AppointmentsEntity, AppointmentDTO> converterUtil
            = new ConverterUtil<>(AppointmentsEntity.class, AppointmentDTO.class);

    // Cria um novo agendamento
    @Override
    public AppointmentDTO create(AppointmentDTO appointmentDTO) {
        AppointmentsEntity appointmentEntity = convertDtoToEntity(appointmentDTO);
        AppointmentsEntity savedEntity = appointmentRepository.save(appointmentEntity);
        sendAppointmentToQueue(savedEntity);
        return convertEntityToDto(savedEntity);
    }

    // Atualiza um agendamento existente
    @Override
    public AppointmentDTO update(AppointmentDTO appointmentDTO) {
        AppointmentsEntity existingAppointment = findAppointmentById(appointmentDTO.getId());
        AppointmentsEntity appointmentEntity = convertDtoToEntity(appointmentDTO);
        // Preserva a data de atualização existente
        appointmentEntity.setUpdatedAt(existingAppointment.getUpdatedAt());
        AppointmentsEntity updatedEntity = appointmentRepository.save(appointmentEntity);
        sendAppointmentToQueue(updatedEntity);
        return convertEntityToDto(updatedEntity);
    }

    // Envia o agendamento para a fila via BrokerService
    private void sendAppointmentToQueue(AppointmentsEntity appointmentEntity) {
        CustomerDTO customerDTO = appointmentEntity.getCustomer() != null
                ? modelMapper.map(appointmentEntity.getCustomer(), CustomerDTO.class) : null;
        BeautyProcedureDTO beautyProcedureDTO = appointmentEntity.getBeauteProcedure() != null
                ? modelMapper.map(appointmentEntity.getBeauteProcedure(), BeautyProcedureDTO.class) : null;

        FullAppointmentDTO fullAppointmentDTO = FullAppointmentDTO.builder()
                .id(appointmentEntity.getId())
                .dateTime(appointmentEntity.getDateTime())
                .appointmentsOpen(appointmentEntity.getAppointmentOpen())
                .customer(customerDTO)
                .beautyProcedure(beautyProcedureDTO)
                .build();
        brokerService.send("appointments", fullAppointmentDTO);
    }

    // Deleta um agendamento pelo ID
    @Override
    public void deleteById(Long id) {
        AppointmentsEntity appointmentEntity = findAppointmentById(id);
        appointmentRepository.delete(appointmentEntity);
    }

    // Associa um cliente e um procedimento de beleza a um agendamento
    @Override
    public AppointmentDTO setCustomerToAppointment(AppointmentDTO appointmentDTO) {
        CustomerEntity customer = findCustomerById(appointmentDTO.getCustomer());
        BeautyProceduresEntity beautyProcedure = findBeautyProcedureById(appointmentDTO.getBeautyProcedure());
        AppointmentsEntity appointment = findAppointmentById(appointmentDTO.getId());

        // Atualiza o agendamento com o cliente e o procedimento de beleza
        appointment.setCustomer(customer);
        appointment.setBeauteProcedure(beautyProcedure);
        appointment.setAppointmentOpen(false);

        AppointmentsEntity updatedAppointment = appointmentRepository.save(appointment);
        sendAppointmentToQueue(updatedAppointment);
        return buildAppointmentDTO(updatedAppointment);
    }

    // Encontra um agendamento pelo ID ou lança exceção se não encontrado
    private AppointmentsEntity findAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with ID: " + id));
    }

    // Encontra um procedimento de beleza pelo ID ou lança exceção se não encontrado
    private BeautyProceduresEntity findBeautyProcedureById(Long id) {
        return beautyProcedureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Beauty procedure not found with ID: " + id));
    }

    // Encontra um cliente pelo ID ou lança exceção se não encontrado
    private CustomerEntity findCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id));
    }

    // Constrói um DTO a partir de uma entidade de agendamento
    private AppointmentDTO buildAppointmentDTO(AppointmentsEntity appointmentEntity) {
        return AppointmentDTO.builder()
                .id(appointmentEntity.getId())
                .beautyProcedure(appointmentEntity.getBeauteProcedure().getId())
                .dateTime(appointmentEntity.getDateTime())
                .appointmentsOpen(appointmentEntity.getAppointmentOpen())
                .customer(appointmentEntity.getCustomer().getId())
                .build();
    }

    // Converte um DTO para uma entidade
    private AppointmentsEntity convertDtoToEntity(AppointmentDTO appointmentDTO) {
        return converterUtil.convertToSource(appointmentDTO);
    }

    // Converte uma entidade para um DTO
    private AppointmentDTO convertEntityToDto(AppointmentsEntity appointmentEntity) {
        return converterUtil.convertToTarget(appointmentEntity);
    }
}