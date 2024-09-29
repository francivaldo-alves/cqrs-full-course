package com.f3pro.beautique.ms_sync.listeners.impl;

import com.f3pro.beautique.ms_sync.dtos.appointments.FullAppointmentDTO;
import com.f3pro.beautique.ms_sync.dtos.beuatyprocedures.BeautyProcedureDTO;
import com.f3pro.beautique.ms_sync.dtos.customers.CustomerDTO;
import com.f3pro.beautique.ms_sync.listeners.ListenerConfig;
import com.f3pro.beautique.ms_sync.services.SyncService;
import com.f3pro.beautique.ms_sync.utils.SyncLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQListenerConfig implements ListenerConfig {

    private final ObjectMapper objectMapper;
    private final SyncService syncService;

    public RabbitMQListenerConfig(ObjectMapper objectMapper, SyncService syncService) {
        this.objectMapper = objectMapper;
        this.syncService = syncService;
    }

    @RabbitListener(queues = "customerQueue")
    @Override
    public void listenToCustomerQueue(String messege) {
        try {
            CustomerDTO customer = objectMapper.readValue(messege, CustomerDTO.class);
            syncService.syncCustomer(customer);
            SyncLogger.info("Messe received from queue customerQueue: " + customer.toString());
        } catch (Exception e) {
            SyncLogger.error(" Error listem customer queue " + e.getMessage());
        }
    }

    @RabbitListener(queues = "appointmentQueue")
    @Override
    public void listenToAppointmentQueue(String messege) {

        try {
            FullAppointmentDTO fullAppointment = objectMapper.readValue(messege, FullAppointmentDTO.class);
            syncService.syncAppointment(fullAppointment);
            SyncLogger.info("Messe received from queue appointmentQueue: " + fullAppointment.toString());
        } catch (Exception e) {
            SyncLogger.error(" Error listen appointmentQueue queue " + e.getMessage());
        }
    }

    @RabbitListener(queues = "beautyProcedureQueue")
    @Override
    public void listenToBeautyProcedureQueue(String messege) {
        try {
            BeautyProcedureDTO beautyProcedures = objectMapper.readValue(messege, BeautyProcedureDTO.class);
          syncService.syncBeautyProcedures(beautyProcedures);
            SyncLogger.info("Messe received from queue beautyProcedureQueue: " + beautyProcedures.toString());
        } catch (Exception e) {
            SyncLogger.error(" Error listen beautyProcedureQueue queue " + e.getMessage());
        }

    }
}
