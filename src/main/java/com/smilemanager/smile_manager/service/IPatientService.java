package com.smilemanager.smile_manager.service;

import java.util.List;

import com.smilemanager.smile_manager.DTO.patient.PatientRequestDTO;
import com.smilemanager.smile_manager.DTO.patient.PatientResponseDTO;

public interface IPatientService {

    public PatientResponseDTO save(PatientRequestDTO patient);

    public PatientResponseDTO findById(Long id);

    public void delete(Long id);

    public List<PatientResponseDTO> findAll();

    public PatientResponseDTO update(Long id, PatientRequestDTO patientDetails);
}
