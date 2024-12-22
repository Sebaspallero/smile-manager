package com.smilemanager.smile_manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.smilemanager.smile_manager.DTO.patient.PatientRequestDTO;
import com.smilemanager.smile_manager.DTO.patient.PatientResponseDTO;
import com.smilemanager.smile_manager.exception.DuplicateEmailException;
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
    public PatientResponseDTO save(PatientRequestDTO patientDTO) {
        try {
            Patient patientEntity = patientMapper.toEntity(patientDTO);
            Patient savedPatient = patientRepository.save(patientEntity);
            return patientMapper.toDTO(savedPatient);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException("Email already exists: " + patientDTO.getEmail());
        }
    }

    @Override
    public PatientResponseDTO findById(Long id) {
        Patient patientToLook = patientRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
        return patientMapper.toDTO(patientToLook);
    }

    @Override
    public void delete(Long id) {
        Patient patientToDelete = patientRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
        patientRepository.delete(patientToDelete);
    }

    @Override
    public List<PatientResponseDTO> findAll() {
         List<Patient> patients = patientRepository.findAll();

        List<PatientResponseDTO> patientsDTO = new ArrayList<>();

        for (Patient patient : patients) {
            patientsDTO.add(patientMapper.toDTO(patient));
        }

        return patientsDTO;
    }

    @Override
    public PatientResponseDTO update(Long id, PatientRequestDTO patientDetails) {
        Patient patientToUpdate = patientRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
        
        patientMapper.updateEntity(patientToUpdate, patientDetails);

        Patient updatedPatient = patientRepository.save(patientToUpdate);

        return patientMapper.toDTO(updatedPatient);
    }
}
