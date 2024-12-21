package com.smilemanager.smile_manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smilemanager.smile_manager.DTO.dentist.DentistRequestDTO;
import com.smilemanager.smile_manager.exception.ResourceNotFoundException;
import com.smilemanager.smile_manager.mapper.DentistMapper;
import com.smilemanager.smile_manager.model.Dentist;
import com.smilemanager.smile_manager.repository.DentistRepository;
import com.smilemanager.smile_manager.service.IDentistService;

@Service
public class DentistServiceImpl implements IDentistService {

    private DentistRepository dentistRepository;
    private DentistMapper dentistMapper;

    @Autowired
    public DentistServiceImpl(DentistRepository dentistRepository, DentistMapper dentistMapper) {
        this.dentistRepository = dentistRepository;
        this.dentistMapper = dentistMapper;
    }

    @Override
    public Dentist save(Dentist dentist) {
        return dentistRepository.save(dentist);
    }

    @Override
    public Dentist findById(Long id) {
        return dentistRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Dentist not found with id: " + id));
    }

    @Override
    public void delete(Long id) {
        Dentist dentistToDelete = findById(id);
        dentistRepository.delete(dentistToDelete);
    }

    @Override
    public List<Dentist> findAll() {
        return dentistRepository.findAll();
    }

    @Override
    public Dentist update(Long id, DentistRequestDTO dentistDetails) {
        Dentist dentistToUpdate = findById(id);
        dentistMapper.updateEntity(dentistToUpdate, dentistDetails);
        return dentistRepository.save(dentistToUpdate);
    }

    

}
