package com.proyecto_policlinico.spring.policlinico_peruano_italiano.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

public class SpecialtyEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_specialty")
    private int idSpecialty;

    @Column(name = "name_specialty")
    private String nameSpecialty;

    @ManyToMany(mappedBy = "specialties")
    @JsonIgnore
    @ToString.Exclude
    private Set<DoctorEntity> doctors;

}
