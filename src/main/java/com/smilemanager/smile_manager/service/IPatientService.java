package com.smilemanager.smile_manager.service;

import java.util.List;

import com.smilemanager.smile_manager.DTO.patient.PatientRequestDTO;
import com.smilemanager.smile_manager.model.Patient;

public interface IPatientService {

    public Patient save(Patient patient);

    public Patient findById(Long id);

    public void delete(Long id);

    public List<Patient> findAll();

    public Patient update(Long id, PatientRequestDTO patientDetails);
}
