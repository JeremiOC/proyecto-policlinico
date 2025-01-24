package com.proyecto_policlinico.spring.policlinico_peruano_italiano.services;

import java.util.List;
import java.util.Optional;

import com.proyecto_policlinico.spring.policlinico_peruano_italiano.entities.dto.DoctorDTO;
import com.proyecto_policlinico.spring.policlinico_peruano_italiano.entities.dto.DoctorResponseDTO;

public interface DoctorService {

    List<DoctorResponseDTO> listDoctors();
    Optional<DoctorResponseDTO> findById(int id);
    DoctorResponseDTO createDoctor(DoctorDTO docDto);
    Optional<DoctorResponseDTO> updateDoctor(int id, DoctorDTO docDto);
    Optional<DoctorResponseDTO> delete(int id);
}
