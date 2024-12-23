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
import com.smilemanager.smile_manager.model.Dentist;
import com.smilemanager.smile_manager.model.Patient;
import com.smilemanager.smile_manager.repository.AppointmentRepository;
import com.smilemanager.smile_manager.repository.DentistRepository;
import com.smilemanager.smile_manager.repository.PatientRepository;
import com.smilemanager.smile_manager.service.IAppointmentService;
import com.smilemanager.smile_manager.service.IEmailService;

@Service
public class AppointmentServiceImpl implements IAppointmentService{

    private AppointmentRepository appointmentRepository;
    private AppointmentMapper appointmentMapper;
    private IEmailService emailService;
    private PatientRepository patientRepository;
    private DentistRepository dentistRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper, IEmailService emailService,  PatientRepository patientRepository, DentistRepository dentistRepository) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
        this.emailService = emailService;
        this.patientRepository = patientRepository;
        this.dentistRepository = dentistRepository;
    }

    @Override
    public AppointmentResponseDTO save(AppointmentRequestDTO appointmentDTO) {
        Patient patient = patientRepository.findById(appointmentDTO.getPatientId())
            .orElseThrow(() -> new ResourceNotFoundException("Appointment could not be made because patient not found with id: " + appointmentDTO.getPatientId()));

        Dentist dentist = dentistRepository.findById(appointmentDTO.getDentistId())
            .orElseThrow(() -> new ResourceNotFoundException("Appointment could not be made because dentist not found with id: " + appointmentDTO.getDentistId()));

        Appointment appointmentEntity = appointmentMapper.toEntity(appointmentDTO);
        Appointment savedAppointment = appointmentRepository.save(appointmentEntity);

        String body = emailService.createAppointmentEmailBody(savedAppointment, dentist);
        String subject = emailService.getAppointmentEmailSubject();

        emailService.sendHtmlMessage(patient.getEmail(), subject, body);

        return appointmentMapper.toDTO(savedAppointment);
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
