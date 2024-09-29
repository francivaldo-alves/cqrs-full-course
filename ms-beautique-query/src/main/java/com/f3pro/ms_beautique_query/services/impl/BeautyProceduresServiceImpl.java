package com.f3pro.ms_beautique_query.services.impl;


import com.f3pro.ms_beautique_query.dtos.beuatyprocedures.BeautyProcedureDTO;
import com.f3pro.ms_beautique_query.repositories.BeautyProcedureRepository;
import com.f3pro.ms_beautique_query.services.BeautyProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeautyProceduresServiceImpl implements BeautyProcedureService {

    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;

    @Override
    public List<BeautyProcedureDTO> listAllBeautyProcedure() {
        try {
            return beautyProcedureRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error listening all beauty procedures");
        }
    }

    @Override
    public List<BeautyProcedureDTO> listByNameIgnoreCase(String name) {
        try {
            return beautyProcedureRepository.findByNameIgnoreCase(name);
        } catch (Exception e) {
            throw new RuntimeException("Error listening all beauty name procedures ");
        }
    }

    @Override
    public List<BeautyProcedureDTO> listByDescriptionIgnoreCase(String email) {
        try {
            return beautyProcedureRepository.findByDescriptionIgnoreCase(email);
        } catch (Exception e) {
            throw new RuntimeException("Error listening all beauty name procedures ");
        }
    }
}