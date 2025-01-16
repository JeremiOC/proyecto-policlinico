package com.proyecto_policlinico.spring.policlinico_peruano_italiano.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPatient;

    @Column(name = "name_patient")
    private String namePatient;

    @Column(name = "lastname_pat")    
    private String lastnamePat;

    private String phone;

    private String dni;


    @Column(name = "date_birth")
    private LocalDate dateBirth;

    private String address;

    private String mail;

}
