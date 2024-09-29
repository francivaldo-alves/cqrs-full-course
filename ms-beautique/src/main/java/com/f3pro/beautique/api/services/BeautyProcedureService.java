package com.f3pro.beautique.api.services;

import com.f3pro.beautique.api.dtos.BeautyProcedureDTO;

public interface BeautyProcedureService {

    BeautyProcedureDTO create(BeautyProcedureDTO beautyProcedureDTO);

    void delete(Long id);

    BeautyProcedureDTO update(BeautyProcedureDTO beautyProcedureDTO);
}
