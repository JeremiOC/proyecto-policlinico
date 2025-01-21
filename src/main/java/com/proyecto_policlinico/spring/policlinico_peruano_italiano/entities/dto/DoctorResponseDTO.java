package com.proyecto_policlinico.spring.policlinico_peruano_italiano.entities.dto;

import java.time.LocalDate;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponseDTO {
    private int idDoctor;
    private String nameDoc;
    private String lastnameDoc;
    private LocalDate date_birth;
    private String dni;
    private String phone;
    private Set<SpecialtyResponseDTO> specialties;
}
