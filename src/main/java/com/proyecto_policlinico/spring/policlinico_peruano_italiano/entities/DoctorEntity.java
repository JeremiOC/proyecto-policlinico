package com.proyecto_policlinico.spring.policlinico_peruano_italiano.entities;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class DoctorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doctor")
    private int idDoctor;

    @Column(name = "name_doc")
    private String nameDoc;

    @Column(name = "lastname_doc")
    private String lastnameDoc;

    @Column(name = "date_birth")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date_birth;

    private String dni;

    private String phone;

    @ManyToMany
    @JoinTable(name = "doctor_specialty",
    joinColumns = @JoinColumn(name="id_doctor"),
    inverseJoinColumns = @JoinColumn(name = "id_specialty")
    )
    @ToString.Exclude
    private Set<SpecialtyEntity> specialties;
    
}
