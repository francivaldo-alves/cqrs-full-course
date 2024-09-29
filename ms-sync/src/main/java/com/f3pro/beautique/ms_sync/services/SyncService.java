package com.f3pro.beautique.ms_sync.services;

import com.f3pro.beautique.ms_sync.dtos.appointments.FullAppointmentDTO;
import com.f3pro.beautique.ms_sync.dtos.beuatyprocedures.BeautyProcedureDTO;
import com.f3pro.beautique.ms_sync.dtos.customers.CustomerDTO;

public interface SyncService {
    void syncCustomer(CustomerDTO customer);
    void syncAppointment(FullAppointmentDTO appointment);
    void syncBeautyProcedures(BeautyProcedureDTO beautyProceduresDTO);
}
