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
public class MedicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMedication;
    
    @Column(name = "cod_medication")
    private String codMedication;
    @Column(name="name_medication")
    private String nameMedication;
    @Column(name="description_medi")
    private String descriptionMed;    
    private LocalDate entry_date;
    private LocalDate expiration_date;
    private int stock;
    private double price;
    
}
