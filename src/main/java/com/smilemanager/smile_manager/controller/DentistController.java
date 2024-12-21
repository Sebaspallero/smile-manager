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
import com.smilemanager.smile_manager.mapper.DentistMapper;
import com.smilemanager.smile_manager.model.Dentist;
import com.smilemanager.smile_manager.service.IDentistService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/dentist")
public class DentistController {

    private IDentistService dentistService;
    private DentistMapper dentistMapper;

    @Autowired
    public DentistController(IDentistService dentistService, DentistMapper dentistMapper) {
        this.dentistService = dentistService;
        this.dentistMapper = dentistMapper;
    }

    @PostMapping
    public ResponseEntity<DentistResponseDTO> save(@Valid @RequestBody DentistRequestDTO dentistRequest) {
        Dentist dentist = dentistMapper.toEntity(dentistRequest);
        Dentist dentistSaved = dentistService.save(dentist);
        return ResponseEntity.status(HttpStatus.CREATED).body(dentistMapper.toDTO(dentistSaved));
    }

    @GetMapping("/{id}")
    public ResponseEntity <DentistResponseDTO> findById(@PathVariable Long id){
        Dentist dentist = dentistService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(dentistMapper.toDTO(dentist));
    }

    @GetMapping
    public ResponseEntity <List<DentistResponseDTO>> findAll(){
        List <DentistResponseDTO> dentistList = dentistService.findAll().stream().map(dentist -> dentistMapper.toDTO(dentist)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(dentistList);
    }

    @PutMapping("/{id}")
    public ResponseEntity <DentistResponseDTO> update(@PathVariable Long id, @Valid @RequestBody DentistRequestDTO dentistDetails){
        Dentist dentistToUpdate = dentistService.update(id, dentistDetails);
        return ResponseEntity.status(HttpStatus.OK).body(dentistMapper.toDTO(dentistToUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> delete(@PathVariable Long id){
        dentistService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
