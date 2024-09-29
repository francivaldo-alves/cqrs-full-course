package com.f3pro.beautique.ms_sync.services;

import com.f3pro.beautique.ms_sync.dtos.appointments.FullAppointmentDTO;
import com.f3pro.beautique.ms_sync.dtos.beuatyprocedures.BeautyProcedureDTO;
import com.f3pro.beautique.ms_sync.dtos.customers.CustomerDTO;

public interface AppointmentService {

    void saveAppointment(FullAppointmentDTO appointment);
    void updateAppointmentCustomer(CustomerDTO customer);
    void updateAppointmentBeautyProcedures(BeautyProcedureDTO beautyProceduresDTO);
}
