package com.f3pro.beautique.ms_sync.services.impl;

import com.f3pro.beautique.ms_sync.dtos.appointments.FullAppointmentDTO;
import com.f3pro.beautique.ms_sync.dtos.beuatyprocedures.BeautyProcedureDTO;
import com.f3pro.beautique.ms_sync.dtos.customers.CustomerDTO;
import com.f3pro.beautique.ms_sync.services.AppointmentService;
import com.f3pro.beautique.ms_sync.services.BeautyProcedureService;
import com.f3pro.beautique.ms_sync.services.CustomerService;
import com.f3pro.beautique.ms_sync.services.SyncService;
import com.f3pro.beautique.ms_sync.utils.SyncLogger;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SyncServiceImpl implements SyncService {

    private final AppointmentService appointmentService;
    private final CustomerService custumerService;
    private final BeautyProcedureService beautyProcedureService;

    public SyncServiceImpl(AppointmentService appointmentService, CustomerService custumerService, BeautyProcedureService beautyProcedureService) {
        this.appointmentService = appointmentService;
        this.custumerService = custumerService;
        this.beautyProcedureService = beautyProcedureService;
    }

    @Override
    public void syncCustomer(CustomerDTO customer) {
        try {
            SyncLogger.info("Saving customer: " + customer.getId());
            custumerService.saveCustomer(customer);
            SyncLogger.info("Updating  appointment customer: " + customer.getId());
            appointmentService.updateAppointmentCustomer(customer);
        } catch (Exception e) {
            SyncLogger.error("Error saving customer: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void syncAppointment(FullAppointmentDTO appointment) {

        try {
            SyncLogger.info("Saving appointment: " + appointment.getId());
            appointmentService.saveAppointment(appointment);
        }catch (Exception e) {
            SyncLogger.error("Error saving appointment: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }

    }

    @Override
    public void syncBeautyProcedures(BeautyProcedureDTO beautyProceduresDTO) {
        try{
            SyncLogger.info("Saving beautyProceduresDTO: " + beautyProceduresDTO.getId());
            beautyProcedureService.saveBeautyProcedure(beautyProceduresDTO);
            SyncLogger.info("updating appointment beauty ProceduresDTO: " + beautyProceduresDTO.getId());
            appointmentService.updateAppointmentBeautyProcedures(beautyProceduresDTO);
        }catch (Exception e){
            SyncLogger.error("Error saving beauty procedures: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }

    }
}
