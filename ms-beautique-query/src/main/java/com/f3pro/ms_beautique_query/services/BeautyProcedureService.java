package com.f3pro.ms_beautique_query.services;


import com.f3pro.ms_beautique_query.dtos.beuatyprocedures.BeautyProcedureDTO;


import java.util.List;

public interface BeautyProcedureService {

    List<BeautyProcedureDTO> listAllBeautyProcedure();
    List<BeautyProcedureDTO> listByNameIgnoreCase(String name);
    List<BeautyProcedureDTO> listByDescriptionIgnoreCase(String email);

}

