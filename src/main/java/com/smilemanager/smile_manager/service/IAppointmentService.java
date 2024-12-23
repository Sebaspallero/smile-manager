package com.smilemanager.smile_manager.service;

import java.util.List;

import com.smilemanager.smile_manager.DTO.appointment.AppointmentRequestDTO;
import com.smilemanager.smile_manager.DTO.appointment.AppointmentResponseDTO;

public interface IAppointmentService {
    
    public AppointmentResponseDTO save(Long availabilityId, Long patientId);

    public AppointmentResponseDTO findById(Long id);

    public void delete(Long id);

    public List<AppointmentResponseDTO> findAll();

    public AppointmentResponseDTO update(Long id, AppointmentRequestDTO appointmentDetails);
}
