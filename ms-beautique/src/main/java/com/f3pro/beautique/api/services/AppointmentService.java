package com.f3pro.beautique.api.services;

import com.f3pro.beautique.api.dtos.AppointmentDTO;


public interface AppointmentService {
    AppointmentDTO create(AppointmentDTO appointmentDTO);
    AppointmentDTO update(AppointmentDTO appointmentDTO);
    void deleteById(Long id);
    AppointmentDTO setCustomerToAppointment(AppointmentDTO appointmentDTO);
}
