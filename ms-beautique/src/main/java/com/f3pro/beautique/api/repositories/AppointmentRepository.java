package com.f3pro.beautique.api.repositories;

import com.f3pro.beautique.api.entities.AppointmentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<AppointmentsEntity,Long> {
}
