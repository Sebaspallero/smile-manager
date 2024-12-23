package com.smilemanager.smile_manager.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


import com.smilemanager.smile_manager.DTO.dentistAvaliability.DentistAvaliabilityResponseDTO;

public interface IDentistAvailabilityService {

    public DentistAvaliabilityResponseDTO createAvailability(Long dentistId, LocalDate date, LocalTime time);
    public List <DentistAvaliabilityResponseDTO> findAllAvaliabilities();
    public void deleteAvailability(Long id);
    public DentistAvaliabilityResponseDTO findById(Long id);
}
