package com.f3pro.ms_beautique_query.controllers;

import com.f3pro.ms_beautique_query.dtos.appointments.FullAppointmentDTO;
import com.f3pro.ms_beautique_query.services.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService AppointmentService;


    public AppointmentController(AppointmentService AppointmentService) {
        this.AppointmentService = AppointmentService;
    }
    @GetMapping()
    ResponseEntity<List<FullAppointmentDTO>> listAllBeautyProcedures(){
        return  ResponseEntity.ok(AppointmentService.listAllAppointments());
    }

    @GetMapping( "/customer/{customerId}")
    ResponseEntity<List<FullAppointmentDTO>> listAppointmentsByCustomer(@PathVariable Long customerId){
        return  ResponseEntity.ok(AppointmentService.ListAllAppointmentsByCustomer(customerId));
    }

    @GetMapping( "/beauty-procedure/{beautyProcedureId}")
    ResponseEntity<List<FullAppointmentDTO>> listAppointmentsByBeautyProcedure(@PathVariable Long beautyProcedureId){
        return  ResponseEntity.ok(AppointmentService.listAllAppointmentsByBeautyProcedure(beautyProcedureId));
    }
}
