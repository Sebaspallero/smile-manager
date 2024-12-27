package com.smilemanager.smile_manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smilemanager.smile_manager.DTO.appointment.AppointmentResponseDTO;
import com.smilemanager.smile_manager.exception.AppointmentUnavailableException;
import com.smilemanager.smile_manager.exception.ResourceNotFoundException;
import com.smilemanager.smile_manager.mapper.AppointmentMapper;
import com.smilemanager.smile_manager.model.Appointment;
import com.smilemanager.smile_manager.model.Dentist;
import com.smilemanager.smile_manager.model.DentistAvailability;
import com.smilemanager.smile_manager.model.Patient;
import com.smilemanager.smile_manager.repository.AppointmentRepository;
import com.smilemanager.smile_manager.repository.DentistAvaliabilityRepository;
import com.smilemanager.smile_manager.repository.DentistRepository;
import com.smilemanager.smile_manager.repository.PatientRepository;
import com.smilemanager.smile_manager.service.IAppointmentService;
import com.smilemanager.smile_manager.service.IEmailService;

@Service
public class AppointmentServiceImpl implements IAppointmentService{

    private AppointmentMapper appointmentMapper;
    private IEmailService emailService;
    private AppointmentRepository appointmentRepository;
    private PatientRepository patientRepository;
    private DentistRepository dentistRepository;
    private DentistAvaliabilityRepository dentistAvaliabilityRepository;
    

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper, IEmailService emailService, PatientRepository patientRepository, DentistRepository dentistRepository, DentistAvaliabilityRepository dentistAvaliabilityRepository) 
    {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
        this.emailService = emailService;
        this.patientRepository = patientRepository;
        this.dentistRepository = dentistRepository;
        this.dentistAvaliabilityRepository = dentistAvaliabilityRepository;
    }


    @Override
    public AppointmentResponseDTO save(Long availabilityId, Long patientId) {

        DentistAvailability availability = dentistAvaliabilityRepository.findById(availabilityId)
            .orElseThrow(() -> new ResourceNotFoundException("Appointment could not be made because availability not found with id: " + availabilityId));

        Patient patient = findPatientById(patientId);
        Dentist dentist = findDentistById(availability.getDentist().getId());

        if (!availability.isAvailable()) {
                throw new AppointmentUnavailableException("This time slot is already booked");
            }

        markAvailabilityAsUnavailable(availability);

        Appointment appointmentEntity = appointmentMapper.toEntity(patient, availability);
        Appointment savedAppointment = appointmentRepository.save(appointmentEntity);

        sendAppointmentNotification(savedAppointment, patient, dentist);

        return appointmentMapper.toDTO(savedAppointment, dentist, patient);
    }

    @Override
    public AppointmentResponseDTO findById(Long id) {
        Appointment appointmentToLook = appointmentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
        
        Patient patient = findPatientById(appointmentToLook.getPatient().getId());
        Dentist dentist = findDentistById(appointmentToLook.getDentist().getId());

        return appointmentMapper.toDTO(appointmentToLook, dentist, patient);
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
            Patient patient = findPatientById(appointment.getPatient().getId());
            Dentist dentist = findDentistById(appointment.getDentist().getId());
            appointmentDTOS.add(appointmentMapper.toDTO(appointment, dentist, patient));
        }

        return appointmentDTOS;
    }

    @Override
    public AppointmentResponseDTO update(Long id, AppointmentResponseDTO appointmentDetails) {
        Appointment appointmentToUpdate = appointmentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
        
        Patient patient = findPatientById(appointmentToUpdate.getPatient().getId());
        Dentist dentist = findDentistById(appointmentToUpdate.getDentist().getId());

        appointmentMapper.updateEntity(appointmentToUpdate, appointmentDetails);

        Appointment updatedAppointment = appointmentRepository.save(appointmentToUpdate);

        return appointmentMapper.toDTO(updatedAppointment, dentist, patient);
    }

    private Patient findPatientById(Long patientId) {
        return patientRepository.findById(patientId)
            .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + patientId));
    }
    
    private Dentist findDentistById(Long dentistId) {
        return dentistRepository.findById(dentistId)
            .orElseThrow(() -> new ResourceNotFoundException("Dentist not found with id: " + dentistId));
    }

    private void sendAppointmentNotification(Appointment appointment, Patient patient, Dentist dentist) {
        String body = emailService.createAppointmentEmailBody(appointment, dentist);
        String subject = emailService.getAppointmentEmailSubject();
        emailService.sendHtmlMessage(patient.getEmail(), subject, body);
    }

    private void markAvailabilityAsUnavailable(DentistAvailability availability) {
        availability.setAvailable(false);
        dentistAvaliabilityRepository.save(availability);
    }
}
