package com.proyecto_policlinico.spring.policlinico_peruano_italiano.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto_policlinico.spring.policlinico_peruano_italiano.entities.PatientEntity;

public interface PatientRepository extends JpaRepository<PatientEntity, Integer> {
    
}
