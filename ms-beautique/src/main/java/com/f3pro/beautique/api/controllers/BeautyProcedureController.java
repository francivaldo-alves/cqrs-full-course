package com.f3pro.beautique.api.controllers;

import com.f3pro.beautique.api.dtos.BeautyProcedureDTO;
import com.f3pro.beautique.api.services.BeautyProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

/**
 * Controlador para gerenciar operações relacionadas a procedimentos de beleza.
 */
@RestController
@RequestMapping("/beauty-procedures")
@Slf4j
public class BeautyProcedureController {

    private final BeautyProcedureService beautyProcedureService;

    @Autowired
    public BeautyProcedureController(BeautyProcedureService beautyProcedureService) {
        this.beautyProcedureService = beautyProcedureService;
    }

    /**
     * Cria um novo procedimento de beleza.
     *
     * @param beautyProcedureDTO o DTO do procedimento de beleza a ser criado.
     * @return a resposta com o DTO do procedimento de beleza criado.
     */
    @PostMapping
    public ResponseEntity<BeautyProcedureDTO> create(@RequestBody BeautyProcedureDTO beautyProcedureDTO) {
        log.info("Criando procedimento de beleza: {}", beautyProcedureDTO);
        BeautyProcedureDTO createdProcedure = beautyProcedureService.create(beautyProcedureDTO);
        return ResponseEntity.ok(createdProcedure);
    }

    /**
     * Deleta um procedimento de beleza pelo ID.
     *
     * @param id o ID do procedimento de beleza a ser deletado.
     * @return a resposta sem corpo (204 No Content).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Deletando procedimento de beleza com ID: {}", id);
        beautyProcedureService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Atualiza um procedimento de beleza existente.
     *
     * @param beautyProcedureDTO o DTO do procedimento de beleza a ser atualizado.
     * @return a resposta com o DTO do procedimento de beleza atualizado.
     */
    @PatchMapping
    public ResponseEntity<BeautyProcedureDTO> update(@RequestBody BeautyProcedureDTO beautyProcedureDTO) {
        log.info("Atualizando procedimento de beleza: {}", beautyProcedureDTO);
        BeautyProcedureDTO updatedProcedure = beautyProcedureService.update(beautyProcedureDTO);
        return ResponseEntity.ok(updatedProcedure);
    }
}