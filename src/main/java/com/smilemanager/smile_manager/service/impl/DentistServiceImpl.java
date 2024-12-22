package com.smilemanager.smile_manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.smilemanager.smile_manager.DTO.dentist.DentistRequestDTO;
import com.smilemanager.smile_manager.DTO.dentist.DentistResponseDTO;
import com.smilemanager.smile_manager.exception.DuplicateEmailException;
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
    public DentistResponseDTO save(DentistRequestDTO dentist) {
        try {
            Dentist dentistEntity = dentistMapper.toEntity(dentist);
            Dentist savedDentist = dentistRepository.save(dentistEntity);
            return dentistMapper.toDTO(savedDentist);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException("Email already exists: " + dentist.getEmail());
        }
    }

    @Override
    public DentistResponseDTO findById(Long id) {
        Dentist dentistToLook = dentistRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Dentist not found with id: " + id));
        return dentistMapper.toDTO(dentistToLook);
    }

    @Override
    public void delete(Long id) {
        Dentist dentistToDelete = dentistRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Dentist not found with id: " + id));
        dentistRepository.delete(dentistToDelete);
    }

    @Override
    public List<DentistResponseDTO> findAll() {
        List<Dentist> dentists = dentistRepository.findAll();

        List<DentistResponseDTO> dentistsDTO = new ArrayList<>();

        for (Dentist dentist : dentists) {
            dentistsDTO.add(dentistMapper.toDTO(dentist));
        }

        return dentistsDTO;
    }

    @Override
    public DentistResponseDTO update(Long id, DentistRequestDTO dentistDetails) {
        Dentist dentistToUpdate = dentistRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Dentist not found with id: " + id));

        dentistMapper.updateEntity(dentistToUpdate, dentistDetails);

        Dentist updatedDentist = dentistRepository.save(dentistToUpdate);

        return dentistMapper.toDTO(updatedDentist);
    }
}
