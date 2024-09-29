package com.f3pro.beautique.api.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="appointments")
public class AppointmentsEntity extends BaseEntity{

@Column(nullable = false, updatable = false)
 private LocalDateTime dateTime;
 
 @Column(nullable = false)
 private Boolean appointmentOpen;
 
 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name="customer_id", nullable = true)
 @ToString.Exclude
 @EqualsAndHashCode.Exclude
 private CustomerEntity customer;
 
 

 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name="beauty_procedure_id", nullable = true)
 @ToString.Exclude
 @EqualsAndHashCode.Exclude
 private BeautyProceduresEntity beauteProcedure;
 
}
