package com.smilemanager.smile_manager.mapper;

import org.springframework.stereotype.Component;

import com.smilemanager.smile_manager.DTO.appointment.AppointmentRequestDTO;
import com.smilemanager.smile_manager.DTO.appointment.AppointmentResponseDTO;
import com.smilemanager.smile_manager.model.Appointment;
import com.smilemanager.smile_manager.model.Dentist;
import com.smilemanager.smile_manager.model.DentistAvailability;
import com.smilemanager.smile_manager.model.Patient;

@Component
public class AppointmentMapper {

    public Appointment toEntity(Long patientId, DentistAvailability availability) {
        Appointment appointment = new Appointment();

        Patient patient = new Patient();
        patient.setId(patientId);

        Dentist dentist = new Dentist();
        dentist.setId(availability.getDentist().getId());
       
        appointment.setPatient(patient);
        appointment.setDentist(dentist);
        appointment.setDate(availability.getDate());
        appointment.setTime(availability.getTime());

        return appointment;
    }

    public AppointmentResponseDTO toDTO(Appointment appointment) {
        if (appointment == null) {
            return null;
            
        }

        AppointmentResponseDTO appointmentDTO = new AppointmentResponseDTO();

        appointmentDTO.setId(appointment.getId());
        appointmentDTO.setPatient(appointment.getPatient().getId());
        appointmentDTO.setDentist(appointment.getDentist().getId());
        appointmentDTO.setDate(appointment.getDate());
        appointmentDTO.setTime(appointment.getTime());

        return appointmentDTO;
    }

    public void updateEntity(Appointment appointment, AppointmentRequestDTO dto) {
        /* appointment.setDate(dto.getDate());
        appointment.setTime(dto.getTime()); */
    }
}
