package com.f3pro.beautique.api.controllers;

import com.f3pro.beautique.api.dtos.AppointmentDTO;
import com.f3pro.beautique.api.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import lombok.extern.slf4j.Slf4j;

/**
 * Controlador para gerenciar operações relacionadas a agendamentos.
 */
@RestController
@RequestMapping("/appointments")
@Slf4j
public class AppointmentsController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentsController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    /**
     * Cria um novo agendamento.
     *
     * @param appointmentDTO o DTO do agendamento a ser criado.
     * @return a resposta com o DTO do agendamento criado.
     */
    @PostMapping
    public ResponseEntity<AppointmentDTO> create(@RequestBody AppointmentDTO appointmentDTO) {
        log.info("Criando agendamento: {}", appointmentDTO);
        AppointmentDTO createdAppointment = appointmentService.create(appointmentDTO);
        return ResponseEntity.ok(createdAppointment);
    }

    /**
     * Atualiza um agendamento existente.
     *
     * @param appointmentDTO o DTO do agendamento a ser atualizado.
     * @return a resposta com o DTO do agendamento atualizado.
     */
    @PatchMapping
    public ResponseEntity<AppointmentDTO> update(@RequestBody AppointmentDTO appointmentDTO) {
        log.info("Atualizando agendamento: {}", appointmentDTO);
        AppointmentDTO updatedAppointment = appointmentService.update(appointmentDTO);
        return ResponseEntity.ok(updatedAppointment);
    }

    /**
     * Deleta um agendamento pelo ID.
     *
     * @param id o ID do agendamento a ser deletado.
     * @return a resposta sem corpo (204 No Content).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.info("Deletando agendamento com ID: {}", id);
        appointmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Associa um cliente a um agendamento.
     *
     * @param appointmentDTO o DTO do agendamento com informações do cliente.
     * @return a resposta com o DTO do agendamento atualizado.
     */
    @PutMapping
    public ResponseEntity<AppointmentDTO> setCustomerToAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        log.info("Associando cliente ao agendamento: {}", appointmentDTO);
        AppointmentDTO updatedAppointment = appointmentService.setCustomerToAppointment(appointmentDTO);
        return ResponseEntity.ok(updatedAppointment);
    }
}