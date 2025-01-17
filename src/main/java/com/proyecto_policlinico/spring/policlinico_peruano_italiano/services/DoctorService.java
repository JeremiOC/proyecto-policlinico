package com.proyecto_policlinico.spring.policlinico_peruano_italiano.services;

import java.util.List;
import java.util.Optional;

import com.proyecto_policlinico.spring.policlinico_peruano_italiano.entities.DoctorEntity;
import com.proyecto_policlinico.spring.policlinico_peruano_italiano.entities.dto.DoctorDTO;

public interface DoctorService {

    List<DoctorEntity> listDoctors();
    Optional<DoctorEntity> findById(int id);
    DoctorEntity createDoctor(DoctorDTO docDto);
    Optional<DoctorEntity> updateDoctor(int id, DoctorDTO docDto);
    Optional<DoctorDTO> delete(int id);
}
