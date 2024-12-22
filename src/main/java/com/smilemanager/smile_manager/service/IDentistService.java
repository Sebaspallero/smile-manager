package com.smilemanager.smile_manager.service;

import java.util.List;

import com.smilemanager.smile_manager.DTO.dentist.DentistRequestDTO;
import com.smilemanager.smile_manager.DTO.dentist.DentistResponseDTO;

public interface IDentistService {

    public DentistResponseDTO save(DentistRequestDTO dentist);

    public DentistResponseDTO findById(Long id);

    public void delete(Long id);

    public List<DentistResponseDTO> findAll();

    public DentistResponseDTO update(Long id, DentistRequestDTO dentistDetails);
}
