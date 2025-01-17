package com.proyecto_policlinico.spring.policlinico_peruano_italiano.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto_policlinico.spring.policlinico_peruano_italiano.entities.SpecialtyEntity;

public interface SpecialtyRepository extends JpaRepository<SpecialtyEntity,Integer> {
    Set<SpecialtyEntity> findByIdIn(Set<Integer> ids);

}
