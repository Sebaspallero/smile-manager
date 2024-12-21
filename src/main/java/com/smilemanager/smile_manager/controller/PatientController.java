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

import com.smilemanager.smile_manager.DTO.patient.PatientRequestDTO;
import com.smilemanager.smile_manager.DTO.patient.PatientResponseDTO;
import com.smilemanager.smile_manager.mapper.PatientMapper;
import com.smilemanager.smile_manager.model.Patient;
import com.smilemanager.smile_manager.service.IPatientService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/patient")
public class PatientController {

    private IPatientService patientService;
    private PatientMapper patientMapper;

    @Autowired
    public PatientController(IPatientService patientService, PatientMapper patientMapper){
        this.patientService = patientService;
        this.patientMapper = patientMapper;
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> save(@Valid @RequestBody PatientRequestDTO patientRequest) {
        Patient patient = patientMapper.toEntity(patientRequest);
        Patient patientSaved = patientService.save(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientMapper.toDTO(patientSaved));
    }

    @PutMapping("/{id}")
    public ResponseEntity <PatientResponseDTO> update(@PathVariable Long id, @RequestBody PatientRequestDTO patientDetails){
        Patient patientToUpdate = patientService.update(id, patientDetails);
        return ResponseEntity.status(HttpStatus.OK).body(patientMapper.toDTO(patientToUpdate));
    }

    @GetMapping
    public ResponseEntity <List<PatientResponseDTO>> findAll(){
        List<PatientResponseDTO> patientList = patientService.findAll().stream().map(patient -> patientMapper.toDTO(patient)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(patientList);
    }


    @GetMapping("/{id}")
    public ResponseEntity <PatientResponseDTO> findById(@PathVariable Long id){
        Patient patient = patientService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(patientMapper.toDTO(patient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> delete(@PathVariable Long id){
        patientService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}   
