package com.proyecto_policlinico.spring.policlinico_peruano_italiano.entities.dto;

import com.proyecto_policlinico.spring.policlinico_peruano_italiano.entities.DoctorEntity;

public class DoctorMapper {
    public static DoctorDTO toDto(DoctorEntity doctorEntity){
        return new DoctorDTO(doctorEntity.getNameDoc(),
                            doctorEntity.getLastnameDoc(),
                            doctorEntity.getDate_birth(),
                            doctorEntity.getDni(),
                            doctorEntity.getPhone(),
                            doctorEntity.getSpecialties());
    }
    public static DoctorEntity toEntity(DoctorDTO docDto){
       DoctorEntity doctorEntity = new DoctorEntity();
       doctorEntity.setNameDoc(docDto.getNameDoc());
       doctorEntity.setLastnameDoc(docDto.getLastnameDoc());
       doctorEntity.setDate_birth(docDto.getDate_birth());
       doctorEntity.setDni(docDto.getDni());
       doctorEntity.setPhone(docDto.getPhone());
       doctorEntity.setSpecialties(docDto.getSpecialties());
       return doctorEntity;
    }
}
