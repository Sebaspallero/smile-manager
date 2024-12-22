package com.smilemanager.smile_manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smilemanager.smile_manager.DTO.appointment.AppointmentRequestDTO;
import com.smilemanager.smile_manager.DTO.appointment.AppointmentResponseDTO;
import com.smilemanager.smile_manager.exception.ResourceNotFoundException;
import com.smilemanager.smile_manager.mapper.AppointmentMapper;
import com.smilemanager.smile_manager.model.Appointment;
import com.smilemanager.smile_manager.repository.AppointmentRepository;
import com.smilemanager.smile_manager.service.IAppointmentService;

@Service
public class AppointmentServiceImpl implements IAppointmentService{

    private AppointmentRepository appointmentRepository;
    private AppointmentMapper appointmentMapper;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper){
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }

    @Override
    public AppointmentResponseDTO save(AppointmentRequestDTO appointmentDTO) {
       try {
            Appointment appointmentEntity = appointmentMapper.toEntity(appointmentDTO);
            Appointment savedAppointment = appointmentRepository.save(appointmentEntity);
            return appointmentMapper.toDTO(savedAppointment);
       } catch (Exception e) {
              throw new RuntimeException("Error saving appointment");
       }
    }

    @Override
    public AppointmentResponseDTO findById(Long id) {
        Appointment appointmentToLook = appointmentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));

        return appointmentMapper.toDTO(appointmentToLook);
    }

    @Override
    public void delete(Long id) {
        Appointment appointmentToDelete = appointmentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
        appointmentRepository.delete(appointmentToDelete);
    }

    @Override
    public List<AppointmentResponseDTO> findAll() {
        List<Appointment> appointments = appointmentRepository.findAll();

        List<AppointmentResponseDTO> appointmentDTOS = new ArrayList<>();

        for (Appointment appointment : appointments) {
            appointmentDTOS.add(appointmentMapper.toDTO(appointment));
        }

        return appointmentDTOS;
    }

    @Override
    public AppointmentResponseDTO update(Long id, AppointmentRequestDTO appointmentDetails) {
        Appointment appointmentToUpdate = appointmentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));

        appointmentMapper.updateEntity(appointmentToUpdate, appointmentDetails);

        Appointment updatedAppointment = appointmentRepository.save(appointmentToUpdate);

        return appointmentMapper.toDTO(updatedAppointment);
    }
}
