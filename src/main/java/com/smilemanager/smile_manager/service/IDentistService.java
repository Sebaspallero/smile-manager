package com.smilemanager.smile_manager.service;

import java.util.List;

import com.smilemanager.smile_manager.DTO.dentist.DentistRequestDTO;
import com.smilemanager.smile_manager.model.Dentist;

public interface IDentistService {

    public Dentist save(Dentist dentist);

    public Dentist findById(Long id);

    public void delete(Long id);

    public List<Dentist> findAll();

    public Dentist update(Long id, DentistRequestDTO dentistDetails);
}
