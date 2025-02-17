package com.proyecto_policlinico.spring.policlinico_peruano_italiano.controllers;



import java.util.Optional;
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

import com.proyecto_policlinico.spring.policlinico_peruano_italiano.entities.dto.DoctorDTO;
import com.proyecto_policlinico.spring.policlinico_peruano_italiano.entities.dto.DoctorResponseDTO;
import com.proyecto_policlinico.spring.policlinico_peruano_italiano.services.DoctorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping("/create")
    public ResponseEntity<?> createDoc(@Valid @RequestBody DoctorDTO doctor,BindingResult result){
        if(result.hasErrors()){
            return validation(result);
        }
        try{
        DoctorResponseDTO createdDoc = doctorService.createDoctor(doctor);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDoc);
        }catch(RuntimeException ex){
            return ResponseEntity.badRequest().body("Error al crear al doctor : "+ex.getMessage());
        }
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){
       return doctorService.findById(id)
            .<ResponseEntity<?>>map(doctor-> ResponseEntity.ok().body(doctor))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("El doctor con el id: " + id + " no fue encontrado"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable int id,@Valid @RequestBody DoctorDTO doc,BindingResult result){
        if (result.hasErrors()) {
            return validation(result);
        }
        try {
            Optional<DoctorResponseDTO> docUpdate = doctorService.updateDoctor(id, doc);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(docUpdate);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al actualizar el doctor : "+ex.getMessage());
        }
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable int id){
       try {
        Optional<DoctorResponseDTO> deletedDoctor = doctorService.delete(id);
            if(deletedDoctor.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(deletedDoctor.get());
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor no encontrado");
            }
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body("Error al eliminar el doctor : "+ex.getMessage());
        }
        
    }   

    @GetMapping("/findAll")
    public ResponseEntity<?> findAllDocs(){
        try {
            return ResponseEntity.ok().body(doctorService.listDoctors());       
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al listar los doctores: "+ex.getMessage());
        }
    }

    public ResponseEntity<?> validation(BindingResult result){
        return ResponseEntity.badRequest()
                .body(result.getAllErrors().stream()
                .map(error->error.getDefaultMessage())
                .collect(Collectors.toList()));
    }
}
