package com.f3pro.beautique.api.repositories;

import com.f3pro.beautique.api.entities.BeautyProceduresEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeautyProcedureRepository extends JpaRepository<BeautyProceduresEntity,Long> {
}
