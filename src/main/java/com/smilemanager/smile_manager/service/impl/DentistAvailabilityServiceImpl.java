package com.smilemanager.smile_manager.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smilemanager.smile_manager.DTO.dentistAvaliability.DentistAvaliabilityResponseDTO;
import com.smilemanager.smile_manager.exception.DateTimeAlreadyExistsException;
import com.smilemanager.smile_manager.exception.ResourceNotFoundException;
import com.smilemanager.smile_manager.mapper.DentistAvaliabilityMapper;
import com.smilemanager.smile_manager.model.Dentist;
import com.smilemanager.smile_manager.model.DentistAvailability;
import com.smilemanager.smile_manager.repository.DentistAvaliabilityRepository;
import com.smilemanager.smile_manager.repository.DentistRepository;
import com.smilemanager.smile_manager.service.IDentistAvailabilityService;

@Service
public class DentistAvailabilityServiceImpl implements IDentistAvailabilityService{

    private DentistAvaliabilityRepository availabilityRepository;
    private DentistRepository dentistRepository;
    private DentistAvaliabilityMapper dentistAvaliabilityMapper;

    @Autowired
    public DentistAvailabilityServiceImpl(DentistAvaliabilityRepository availabilityRepository, DentistRepository dentistRepository, DentistAvaliabilityMapper dentistAvaliabilityMapper) {
        this.availabilityRepository = availabilityRepository;
        this.dentistRepository = dentistRepository;
        this.dentistAvaliabilityMapper = dentistAvaliabilityMapper;
    }

    @Override
    public DentistAvaliabilityResponseDTO createAvailability(Long dentistId, LocalDate date, LocalTime time) {
        
        Dentist dentist = dentistRepository.findById(dentistId)
                .orElseThrow(() -> new RuntimeException("Dentist not found with ID:" + dentistId));

        boolean exists = availabilityRepository.existsByDentistIdAndDateAndTime(dentistId, date, time);
        if (exists) {
            throw new DateTimeAlreadyExistsException("This date and time are already assigned to the dentist.");
        }

        DentistAvailability availability = new DentistAvailability();
    
        availability.setDentist(dentist);
        availability.setDate(date);
        availability.setTime(time);

        DentistAvailability savedDentistAvailability = availabilityRepository.save(availability);

        return dentistAvaliabilityMapper.toDTO(savedDentistAvailability);
    }

    @Override
    public List<DentistAvaliabilityResponseDTO> findAllAvaliabilities() {
        List<DentistAvailability> availabilities = availabilityRepository.findAll();

        List<DentistAvaliabilityResponseDTO> availabilitiesDTO = new ArrayList<>();

        for (DentistAvailability availability : availabilities) {
            availabilitiesDTO.add(dentistAvaliabilityMapper.toDTO(availability));
        }

        return availabilitiesDTO;
    }

    @Override
    public DentistAvaliabilityResponseDTO findById(Long id) {
        DentistAvailability availabilityToLook = availabilityRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Availability not found with id: " + id));

        return dentistAvaliabilityMapper.toDTO(availabilityToLook);
    }

    @Override
    public void deleteAvailability(Long id) {
        DentistAvailability availabilityToDelete = availabilityRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Availability not found with id: " + id));

        availabilityRepository.delete(availabilityToDelete);
    }

    @Override
    public DentistAvaliabilityResponseDTO updateAvailability(Long id, Long dentistId, LocalDate date, LocalTime time) {
        DentistAvailability availabilityToUpdate = availabilityRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Availability not found with ID: " + id));

        Dentist dentist = dentistRepository.findById(dentistId)
            .orElseThrow(() -> new ResourceNotFoundException("Dentist not found with ID:" + dentistId));

        availabilityToUpdate.setDentist(dentist);
        availabilityToUpdate.setDate(date);
        availabilityToUpdate.setTime(time);

        DentistAvailability updatedAvailability = availabilityRepository.save(availabilityToUpdate);

        return dentistAvaliabilityMapper.toDTO(updatedAvailability);
    }

}
