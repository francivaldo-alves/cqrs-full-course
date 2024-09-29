package com.f3pro.ms_beautique_query.services;


import com.f3pro.ms_beautique_query.dtos.appointments.FullAppointmentDTO;


import java.util.List;

public interface AppointmentService {

    List<FullAppointmentDTO> listAllAppointments();

    List<FullAppointmentDTO> ListAllAppointmentsByCustomer(Long customerId);

    List<FullAppointmentDTO> listAllAppointmentsByBeautyProcedure(Long beautyProcedureId);
}