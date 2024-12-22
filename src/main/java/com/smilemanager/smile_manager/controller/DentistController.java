package com.smilemanager.smile_manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smilemanager.smile_manager.DTO.dentist.DentistRequestDTO;
import com.smilemanager.smile_manager.DTO.dentist.DentistResponseDTO;
import com.smilemanager.smile_manager.service.IDentistService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/dentist")
public class DentistController {

    private IDentistService dentistService;

    @Autowired
    public DentistController(IDentistService dentistService) {
        this.dentistService = dentistService;
    }

    @PostMapping
    public ResponseEntity<DentistResponseDTO> save(@Valid @RequestBody DentistRequestDTO dentistRequest) {
        DentistResponseDTO dentist = dentistService.save(dentistRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(dentist);
    }

    @GetMapping("/{id}")
    public ResponseEntity <DentistResponseDTO> findById(@PathVariable Long id){
        DentistResponseDTO dentist = dentistService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(dentist);
    }

    @GetMapping
    public ResponseEntity <List<DentistResponseDTO>> findAll(){
        List <DentistResponseDTO> dentistList = dentistService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(dentistList);
    }

    @PutMapping("/{id}")
    public ResponseEntity <DentistResponseDTO> update(@PathVariable Long id, @Valid @RequestBody DentistRequestDTO dentistDetails){
        DentistResponseDTO dentist = dentistService.update(id, dentistDetails);
        return ResponseEntity.status(HttpStatus.OK).body(dentist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> delete(@PathVariable Long id){
        dentistService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
