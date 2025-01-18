package com.proyecto_policlinico.spring.policlinico_peruano_italiano.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto_policlinico.spring.policlinico_peruano_italiano.entities.DoctorEntity;
import com.proyecto_policlinico.spring.policlinico_peruano_italiano.entities.SpecialtyEntity;
import com.proyecto_policlinico.spring.policlinico_peruano_italiano.entities.dto.DoctorDTO;
import com.proyecto_policlinico.spring.policlinico_peruano_italiano.repositories.DoctorRepository;
import com.proyecto_policlinico.spring.policlinico_peruano_italiano.repositories.SpecialtyRepository;


@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired 
    private SpecialtyRepository specialtyRepository;
    @Transactional(readOnly = true)
    @Override
    public List<DoctorEntity> listDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Optional<DoctorEntity> findById(int id) {
        return doctorRepository.findById(id);
    }

    @Override
    public DoctorEntity createDoctor(DoctorDTO docDto) {
        DoctorEntity doc = new DoctorEntity();
        doc.setNameDoc(docDto.getNameDoc());
        doc.setLastnameDoc(docDto.getLastnameDoc());
        doc.setDate_birth(docDto.getDate_birth());
        doc.setDni(docDto.getDni());
        doc.setPhone(docDto.getPhone());
        Set<SpecialtyEntity> specs = specialtyRepository.findByIdSpecialtyIn(docDto.getSpecialties());
        /* 
        Set<SpecialtyEntity> specs = docDto.getSpecialties()
        .stream()
        .map(spec-> specialtyRepository.
        findById(spec.getIdSpecialty())
        .orElseThrow(()-> new RuntimeException("Especialidad con id : "+spec.getIdSpecialty()+" no existe")))
        .collect(Collectors.toSet());*/
        doc.setSpecialties(specs);

        return doctorRepository.save(doc);
    }

    @Override
    public Optional<DoctorEntity> updateDoctor(int id, DoctorDTO docDto) {
        Optional<DoctorEntity> opt = doctorRepository.findById(id);
        if(opt.isPresent()){
            DoctorEntity docFound = opt.get();
            docFound.setNameDoc(docDto.getNameDoc());
            docFound.setLastnameDoc(docDto.getLastnameDoc());
            docFound.setDate_birth(docDto.getDate_birth());
            docFound.setPhone(docDto.getPhone());
            Set<SpecialtyEntity> specs = specialtyRepository.findByIdSpecialtyIn(docDto.getSpecialties());
            /* 
            Set<SpecialtyEntity> updateSpecs = docDto.getSpecialties().stream()
                                    .map(spec-> specialtyRepository.findById(spec.getIdSpecialty())
                                    .orElseThrow(()-> new RuntimeException("Especialidad con id : "+spec.getIdSpecialty()+" no existe")))
                                    .collect(Collectors.toSet());
            
            */

            docFound.setSpecialties(specs);
            doctorRepository.save(docFound);
            return Optional.of(docFound);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DoctorEntity> delete(int id) {
        Optional<DoctorEntity> opt = doctorRepository.findById(id);
        if(opt.isPresent()){
            DoctorEntity doc = opt.get();
            doctorRepository.delete(doc);
           return Optional.of(doc);
        }
        return Optional.empty();
    }

}
