package com.proyecto_policlinico.spring.policlinico_peruano_italiano.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_policlinico.spring.policlinico_peruano_italiano.entities.SpecialtyEntity;
import com.proyecto_policlinico.spring.policlinico_peruano_italiano.repositories.SpecialtyRepository;
import com.proyecto_policlinico.spring.policlinico_peruano_italiano.services.SpecialtyService;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {
    
    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Override
    public List<SpecialtyEntity> list() {
        return specialtyRepository.findAll();    
    }

    @Override
    public SpecialtyEntity createSpecialty(SpecialtyEntity spec) {
        SpecialtyEntity specialtyEntity = new SpecialtyEntity();
        specialtyEntity.setNameSpecialty(spec.getNameSpecialty());
        if(specialtyRepository.existsByNameSpecialty(spec.getNameSpecialty())){
            throw new IllegalArgumentException("La especilidad ya existe");
        }
        return specialtyRepository.save(specialtyEntity);
    }

    @Override
    public Optional<SpecialtyEntity> updateSpecialty(int id, SpecialtyEntity spec) {
        Optional<SpecialtyEntity> specOpt = specialtyRepository.findById(id);
        if(specOpt.isPresent()){
            SpecialtyEntity specFound = specOpt.get();
            specFound.setNameSpecialty(spec.getNameSpecialty());
            return Optional.of(specialtyRepository.save(specFound));
        }
        return Optional.empty();
    } 

    @Override
    public Optional<SpecialtyEntity> delete(int id) {
        Optional<SpecialtyEntity> specOptional = specialtyRepository.findById(id);
        if(specOptional.isPresent()){
            SpecialtyEntity specFound = specOptional.get();
            specialtyRepository.delete(specFound);
            return Optional.of(specFound);
        }
        return Optional.empty();
    }

    @Override
    public Optional<SpecialtyEntity> findById(int id) {
       return specialtyRepository.findById(id);
    }

}
