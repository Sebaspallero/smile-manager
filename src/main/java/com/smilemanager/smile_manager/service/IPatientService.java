package com.smilemanager.smile_manager.service;

import java.util.List;
import java.util.Optional;

import com.smilemanager.smile_manager.model.Patient;

public interface IPatientService {

    public Patient save(Patient patient);

    public Optional <Patient> findById(Long id);

    public void delete(Long id);

    public List<Patient> findAll();

    public Patient update(Patient patient);
}
