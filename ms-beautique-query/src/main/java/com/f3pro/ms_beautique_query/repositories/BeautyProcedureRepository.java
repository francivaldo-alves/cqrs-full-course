package com.f3pro.ms_beautique_query.repositories;


import com.f3pro.ms_beautique_query.dtos.beuatyprocedures.BeautyProcedureDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeautyProcedureRepository extends MongoRepository<BeautyProcedureDTO, Long> {

    @Query("{'description': {$regex: ?0, $options: 'i'}}")
    List<BeautyProcedureDTO> findByDescriptionIgnoreCase(String email);

    @Query("{'name': {$regex: ?0, $options: 'i'}}")
    List<BeautyProcedureDTO> findByNameIgnoreCase(String name);
}
