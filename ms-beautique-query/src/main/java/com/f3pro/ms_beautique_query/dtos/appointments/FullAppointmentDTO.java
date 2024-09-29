package com.f3pro.ms_beautique_query.dtos.appointments;

import com.f3pro.ms_beautique_query.dtos.beuatyprocedures.BeautyProcedureDTO;
import com.f3pro.ms_beautique_query.dtos.customers.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "appointments")
public class FullAppointmentDTO {

    private Long id;
    private LocalDateTime dateTime;
    private Boolean appointmentsOpen;

    private CustomerDTO customer;
    private BeautyProcedureDTO beautyProcedure;
}