package com.f3pro.beautique.api.services.impl;

import com.f3pro.beautique.api.dtos.BeautyProcedureDTO;
import com.f3pro.beautique.api.entities.BeautyProceduresEntity;
import com.f3pro.beautique.api.repositories.BeautyProcedureRepository;
import com.f3pro.beautique.api.services.BeautyProcedureService;
import com.f3pro.beautique.api.services.BrokerService;
import com.f3pro.beautique.api.utils.ConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BeautyProcedureServiceImpl implements BeautyProcedureService {

    @Autowired
    private BrokerService brokerService;

    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;

    // Utilitário para conversão entre entidade e DTO
    private final ConverterUtil<BeautyProceduresEntity, BeautyProcedureDTO> converterUtil
            = new ConverterUtil<>(BeautyProceduresEntity.class, BeautyProcedureDTO.class);

    // Cria um novo procedimento de beleza e o envia para a fila
    @Override
    public BeautyProcedureDTO create(BeautyProcedureDTO beautyProcedureDTO) {
        BeautyProceduresEntity beautyProceduresEntity = convertDtoToEntity(beautyProcedureDTO);
        BeautyProceduresEntity savedEntity = beautyProcedureRepository.save(beautyProceduresEntity);
        sendBeautyProcedureToQueue(savedEntity);
        return convertEntityToDto(savedEntity);
    }

    // Deleta um procedimento de beleza existente pelo ID
    @Override
    public void delete(Long id) {
        BeautyProceduresEntity entity = findBeautyProcedureById(id);
        beautyProcedureRepository.delete(entity);
    }

    // Atualiza um procedimento de beleza existente e o envia para a fila
    @Override
    public BeautyProcedureDTO update(BeautyProcedureDTO beautyProcedureDTO) {
        BeautyProceduresEntity existingEntity = findBeautyProcedureById(beautyProcedureDTO.getId());
        BeautyProceduresEntity updatedEntity = convertDtoToEntity(beautyProcedureDTO);
        preserveExistingData(existingEntity, updatedEntity);
        BeautyProceduresEntity savedEntity = beautyProcedureRepository.save(updatedEntity);
        sendBeautyProcedureToQueue(savedEntity);
        return convertEntityToDto(savedEntity);
    }

    // Envia o procedimento de beleza para a fila via BrokerService
    private void sendBeautyProcedureToQueue(BeautyProceduresEntity beautyProceduresEntity) {
        BeautyProcedureDTO beautyProcedureDTO = convertEntityToDto(beautyProceduresEntity);
        brokerService.send("beautyProcedures", beautyProcedureDTO);
    }

    // Converte um DTO para uma entidade
    private BeautyProceduresEntity convertDtoToEntity(BeautyProcedureDTO beautyProcedureDTO) {
        return converterUtil.convertToSource(beautyProcedureDTO);
    }

    // Converte uma entidade para um DTO
    private BeautyProcedureDTO convertEntityToDto(BeautyProceduresEntity beautyProceduresEntity) {
        return converterUtil.convertToTarget(beautyProceduresEntity);
    }

    // Preserva dados existentes durante a atualização
    private void preserveExistingData(BeautyProceduresEntity existingEntity, BeautyProceduresEntity updatedEntity) {
        updatedEntity.setAppointments(existingEntity.getAppointments());
        updatedEntity.setCreatedAt(existingEntity.getCreatedAt());
    }

    // Busca um procedimento de beleza pelo ID ou lança exceção se não encontrado
    private BeautyProceduresEntity findBeautyProcedureById(Long id) {
        return beautyProcedureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Beauty Procedure not found with ID: " + id));
    }
}
