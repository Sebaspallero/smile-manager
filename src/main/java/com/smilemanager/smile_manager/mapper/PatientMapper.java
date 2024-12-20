package com.smilemanager.smile_manager.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.smilemanager.smile_manager.DTO.patient.PatientRequestDTO;
import com.smilemanager.smile_manager.DTO.patient.PatientResponseDTO;
import com.smilemanager.smile_manager.model.Patient;

@Component
public class PatientMapper {

    public Patient toEntity(PatientRequestDTO patientRequestDTO) {
        Patient patient = new Patient();
        patient.setName(patientRequestDTO.getName());
        patient.setLastName(patientRequestDTO.getLastName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setBirthDate(patientRequestDTO.getBirthDate());
        patient.setRegistrationDate(LocalDateTime.now());
        return patient;
    }

    public PatientResponseDTO toDTO(Patient patient) {
        PatientResponseDTO patientDTO = new PatientResponseDTO();
        patientDTO.setId(patient.getId());
        patientDTO.setName(patient.getName());
        patientDTO.setLastName(patient.getLastName());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setBirthDate(patient.getBirthDate());
        patientDTO.setRegistrationDate(patient.getRegistrationDate());
        return patientDTO;
    }

    public void updateEntity(Patient patient, PatientRequestDTO dto) {
        patient.setName(dto.getName());
        patient.setLastName(dto.getLastName());
        patient.setEmail(dto.getEmail());
        patient.setBirthDate(dto.getBirthDate());
    }
}
