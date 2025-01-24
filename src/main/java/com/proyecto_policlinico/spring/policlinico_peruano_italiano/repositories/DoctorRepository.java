package com.proyecto_policlinico.spring.policlinico_peruano_italiano.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto_policlinico.spring.policlinico_peruano_italiano.entities.DoctorEntity;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Integer> {
    @Query("SELECT d FROM DoctorEntity d LEFT JOIN FETCH d.specialties WHERE d.idDoctor = ?1")
    Optional<DoctorEntity> findByIdWithSpecialties(@Param("id") int id);

    @Query("SELECT d FROM DoctorEntity d LEFT JOIN FETCH d.specialties")
    List<DoctorEntity> findAllWithSpecialties();
}
