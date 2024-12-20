package com.smilemanager.smile_manager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smilemanager.smile_manager.model.Patient;
import com.smilemanager.smile_manager.repository.PatientRepository;
import com.smilemanager.smile_manager.service.IPatientService;

@Service
public class PatientServiceImpl implements IPatientService{

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient update(Patient patient) {
        return patientRepository.save(patient);
    }

}
