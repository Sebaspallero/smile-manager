package com.smilemanager.smile_manager.controller;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity <PatientResponseDTO> save(@RequestBody @Valid PatientRequestDTO patientRequest){
        Patient patient = patientMapper.toEntity(patientRequest);
        Patient patientSaved = patientService.save(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientMapper.toDTO(patientSaved));
    }

    @PutMapping("/{id}")
    public ResponseEntity <PatientResponseDTO> update(@PathVariable Long id, @RequestBody PatientRequestDTO patientDetails){
        Optional <Patient> OptionalPatient = patientService.findById(id);

        if(!OptionalPatient.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Patient patientToUpdate = OptionalPatient.get();
        
        patientMapper.updateEntity(patientToUpdate, patientDetails);

        Patient patientUpdated = patientService.save(patientToUpdate);

        return ResponseEntity.status(HttpStatus.OK).body(patientMapper.toDTO(patientUpdated));
    }

    @GetMapping
    public ResponseEntity <List<PatientResponseDTO>> findAll(){
        List<PatientResponseDTO> patientList = patientService.findAll().stream().map(patient -> patientMapper.toDTO(patient)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(patientList);
    }


    @GetMapping("/{id}")
    public ResponseEntity <PatientResponseDTO> findById(@PathVariable Long id){
        Optional <Patient> patient = patientService.findById(id);

        if(!patient.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(patientMapper.toDTO(patient.get()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> delete(@PathVariable Long id){
        Optional <Patient> patient = patientService.findById(id);

        if(patient.isPresent()){
            patientService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}   
