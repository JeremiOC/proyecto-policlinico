package com.proyecto_policlinico.spring.policlinico_peruano_italiano.services.impl;

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
import com.proyecto_policlinico.spring.policlinico_peruano_italiano.entities.dto.DoctorResponseDTO;
import com.proyecto_policlinico.spring.policlinico_peruano_italiano.entities.dto.SpecialtyResponseDTO;
import com.proyecto_policlinico.spring.policlinico_peruano_italiano.repositories.DoctorRepository;
import com.proyecto_policlinico.spring.policlinico_peruano_italiano.repositories.SpecialtyRepository;
import com.proyecto_policlinico.spring.policlinico_peruano_italiano.services.DoctorService;


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
    public Optional<DoctorResponseDTO> findById(int id) {
        Optional<DoctorEntity> doc = doctorRepository.findByIdWithSpecialties(id);
        if(doc.isPresent()){
            DoctorEntity docFound = doc.get();
            return Optional.of(convertToResponseDTO(docFound)) ;
        }
        return Optional.empty();
    }

    @Override
    public DoctorResponseDTO  createDoctor(DoctorDTO docDto) {
        DoctorEntity doc = new DoctorEntity();
        doc.setNameDoc(docDto.getNameDoc());
        doc.setLastnameDoc(docDto.getLastnameDoc());
        doc.setDate_birth(docDto.getDate_birth());
        doc.setDni(docDto.getDni());
        doc.setPhone(docDto.getPhone());
        Set<SpecialtyEntity> specs = updateSpecialties(docDto.getSpecialties());
        doc.setSpecialties(specs);
        return convertToResponseDTO(doctorRepository.save(doc));
        
    }

    @Override
    public Optional<DoctorResponseDTO> updateDoctor(int id, DoctorDTO docDto) {
        Optional<DoctorEntity> opt = doctorRepository.findById(id);
        if(opt.isPresent()){
            DoctorEntity docFound = opt.get();
            docFound.setNameDoc(docDto.getNameDoc());
            docFound.setLastnameDoc(docDto.getLastnameDoc());
            docFound.setDate_birth(docDto.getDate_birth());
            docFound.setPhone(docDto.getPhone());          

            Set<SpecialtyEntity> specs = updateSpecialties(docDto.getSpecialties());
            docFound.setSpecialties(specs);


            return Optional.of(convertToResponseDTO(doctorRepository.save(docFound)));
        }
        return Optional.empty();
    }

    @Override
    public Optional<DoctorResponseDTO> delete(int id) {
        Optional<DoctorEntity> opt = doctorRepository.findByIdWithSpecialties(id);
        if(opt.isPresent()){
            DoctorEntity doc = opt.get();
            doctorRepository.delete(doc);
           return Optional.of(convertToResponseDTO(doc));
        }
        return Optional.empty();
        
    }

    private Set<SpecialtyEntity> updateSpecialties(Set<Integer> specialtyIds) {
        Set<SpecialtyEntity> specs = specialtyRepository.findByIdSpecialtyIn(specialtyIds);
    //aqui hago una verificacion donde si specs que representa las especialidades en la base de datos es difernte a los ids que paso por el dto osea q hay un error
        //si en la bd hay una sola especialidad y nosotros en el dtos le pasamos 2 va a saltar a este if y hara la verificacion y lanzara una excepcion       
        if (specs.size() != specialtyIds.size()) {
            Set<Integer> foundIds = specs.stream()
                .map(SpecialtyEntity::getIdSpecialty)
                .collect(Collectors.toSet());
                
            Set<Integer> notFoundIds = specialtyIds.stream()
                .filter(id -> !foundIds.contains(id))
                .collect(Collectors.toSet());
                
            throw new RuntimeException("No se encontraron las siguientes especialidades: " + notFoundIds);
        }
        
        return specs;
    }

    private DoctorResponseDTO convertToResponseDTO(DoctorEntity doctor) {
        DoctorResponseDTO response = new DoctorResponseDTO();
        response.setIdDoctor(doctor.getIdDoctor());
        response.setNameDoc(doctor.getNameDoc());
        response.setLastnameDoc(doctor.getLastnameDoc());
        response.setDate_birth(doctor.getDate_birth());
        response.setDni(doctor.getDni());
        response.setPhone(doctor.getPhone());
        
        Set<SpecialtyResponseDTO> specDtos = doctor.getSpecialties().stream()
            .map(spec -> new SpecialtyResponseDTO(
                spec.getIdSpecialty(),
                spec.getNameSpecialty()))
            .collect(Collectors.toSet());
        
        response.setSpecialties(specDtos);
        return response;
    }
}
