package com.proyecto_policlinico.spring.policlinico_peruano_italiano.services;

import java.util.List;
import java.util.Optional;

import com.proyecto_policlinico.spring.policlinico_peruano_italiano.entities.SpecialtyEntity;

public interface SpecialtyService {
    List<SpecialtyEntity> list();
    SpecialtyEntity createSpecialty(SpecialtyEntity spec);
    Optional<SpecialtyEntity>updateSpecialty(int id, SpecialtyEntity spec);
    Optional<SpecialtyEntity> delete(int id);
    Optional<SpecialtyEntity> findById(int id);

}
