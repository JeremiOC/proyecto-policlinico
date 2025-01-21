package com.proyecto_policlinico.spring.policlinico_peruano_italiano.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpecialtyResponseDTO {
    private int idSpecialty;
    private String nameSpecialty;
}
