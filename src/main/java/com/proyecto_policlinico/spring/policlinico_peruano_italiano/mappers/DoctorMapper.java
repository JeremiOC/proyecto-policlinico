package com.proyecto_policlinico.spring.policlinico_peruano_italiano.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.proyecto_policlinico.spring.policlinico_peruano_italiano.entities.DoctorEntity;
import com.proyecto_policlinico.spring.policlinico_peruano_italiano.entities.dto.DoctorDTO;
import com.proyecto_policlinico.spring.policlinico_peruano_italiano.entities.dto.DoctorResponseDTO;
@Mapper(componentModel = "spring")
public interface DoctorMapper {
    
    @Mapping(target = "specialties",ignore = true)
    @Mapping(target = "idDoctor", ignore = true)  
    DoctorEntity toEntity(DoctorDTO doctorDTO);

    DoctorResponseDTO toResponseDTO(DoctorEntity doctorEntity);

    @Mapping(target = "idDoctor", ignore = true)
    @Mapping(target = "specialties",ignore = true)
    void updateDoctorFromDTO(DoctorDTO docDto, @MappingTarget DoctorEntity docEntity);
}   
