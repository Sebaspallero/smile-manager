package com.smilemanager.smile_manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smilemanager.smile_manager.DTO.patient.PatientRequestDTO;
import com.smilemanager.smile_manager.exception.ResourceNotFoundException;
import com.smilemanager.smile_manager.mapper.PatientMapper;
import com.smilemanager.smile_manager.model.Patient;
import com.smilemanager.smile_manager.repository.PatientRepository;
import com.smilemanager.smile_manager.service.IPatientService;

@Service
public class PatientServiceImpl implements IPatientService{

   
    private PatientRepository patientRepository;
    private PatientMapper patientMapper;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient findById(Long id) {
        return patientRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
    }

    @Override
    public void delete(Long id) {
        Patient patientToDelete = findById(id);
        patientRepository.delete(patientToDelete);
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient update(Long id, PatientRequestDTO patientDetails) {
        Patient patientToUpdate = findById(id);
        patientMapper.updateEntity(patientToUpdate, patientDetails);
        return patientRepository.save(patientToUpdate);
    }

}
