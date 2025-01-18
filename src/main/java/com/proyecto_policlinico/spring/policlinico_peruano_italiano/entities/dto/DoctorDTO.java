package com.proyecto_policlinico.spring.policlinico_peruano_italiano.entities.dto;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO {
    
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nameDoc;

    @NotBlank(message = "El apellido no puede estar vacío")
    private String lastnameDoc;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "La fecha de nacimiento no puede ser nula")
    private LocalDate date_birth;

    @NotBlank(message = "El DNI no puede estar vacío")
    @Size(min = 8, max = 8, message = "El DNI debe tener 8 caracteres")
    private String dni;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Size(min = 9, max = 9, message = "El teléfono debe tener 9 dígitos")
    private String phone;
    
    @NotEmpty(message = "La lista de especialidades no puede estar vacía")
    private Set<Integer> specialties;
    
}
