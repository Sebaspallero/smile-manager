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

import com.smilemanager.smile_manager.model.Patient;
import com.smilemanager.smile_manager.service.IPatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private IPatientService patientService;


    @PostMapping
    public ResponseEntity <Patient> save(@RequestBody Patient patient){
        Patient patientSaved = patientService.save(patient);
        return new ResponseEntity<>(patientSaved, HttpStatus.CREATED);
        
    }

    @PutMapping("/{id}")
    public ResponseEntity <Patient> update(@PathVariable Long id, @RequestBody Patient patientDetails){
        Optional <Patient> OptionalPatient = patientService.findById(id);

        if(!OptionalPatient.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Patient patientToUpdate = OptionalPatient.get();
        
        patientToUpdate.setName(patientDetails.getName());
        patientToUpdate.setLastName(patientDetails.getLastName());
        patientToUpdate.setEmail(patientDetails.getEmail());
        patientToUpdate.setBirthDate(patientDetails.getBirthDate());

        Patient patientUpdated = patientService.save(patientToUpdate);
        return new ResponseEntity<>(patientUpdated, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity <List<Patient>> findAll(){
        List<Patient> patientList = patientService.findAll();
        return new ResponseEntity<>(patientList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity <Patient> findById(@PathVariable Long id){
        Optional <Patient> patient = patientService.findById(id);

        if(!patient.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(patient.get(), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> delete(@PathVariable Long id){
        Optional <Patient> patient = patientService.findById(id);

        if(patient.isPresent()){
            patientService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}   
