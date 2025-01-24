package com.proyecto_policlinico.spring.policlinico_peruano_italiano.controllers;


import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto_policlinico.spring.policlinico_peruano_italiano.entities.SpecialtyEntity;
import com.proyecto_policlinico.spring.policlinico_peruano_italiano.services.SpecialtyService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/specialty")
public class SpecialtyController {
    @Autowired
    private SpecialtyService specialtyService;

    @PostMapping("/create")
    public ResponseEntity<?> createSpec(@Valid @RequestBody SpecialtyEntity spec,BindingResult result){
        if(result.hasErrors()){
            return validation(result);
        }
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(specialtyService.createSpecialty(spec));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al crear la especialidad : "+e.getMessage());
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSpec(@PathVariable int id,@RequestBody SpecialtyEntity spec){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(specialtyService.updateSpecialty(id, spec));   
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al actualizar la especialidad : "+e.getMessage());
        }
    }
    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        try {
            return ResponseEntity.ok().body(specialtyService.list());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error al listar"+e.getMessage());
        }
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){
        try {
            return ResponseEntity.ok().body(specialtyService.findById(id));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error al buscar"+e.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        try {
            return ResponseEntity.ok().body(specialtyService.delete(id));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error al eliminar"+e.getMessage());
        }
    }
    public ResponseEntity<?> validation(BindingResult result){
        return ResponseEntity.badRequest().body(result.getAllErrors().stream().map(error->error.getDefaultMessage()).collect(Collectors.toList()));
    }
}
