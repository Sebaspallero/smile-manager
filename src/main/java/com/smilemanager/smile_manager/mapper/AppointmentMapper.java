package com.smilemanager.smile_manager.mapper;

import org.springframework.stereotype.Component;

import com.smilemanager.smile_manager.DTO.appointment.AppointmentRequestDTO;
import com.smilemanager.smile_manager.DTO.appointment.AppointmentResponseDTO;
import com.smilemanager.smile_manager.model.Appointment;
import com.smilemanager.smile_manager.model.Dentist;
import com.smilemanager.smile_manager.model.Patient;

@Component
public class AppointmentMapper {

    public Appointment toEntity(AppointmentRequestDTO appointmentRequestDTO) {
        Appointment appointment = new Appointment();

        Patient patient = new Patient();
        patient.setId(appointmentRequestDTO.getPatientId());

        Dentist dentist = new Dentist();
        dentist.setId(appointmentRequestDTO.getDentistId());
       
        appointment.setPatient(patient);
        appointment.setDentist(dentist);
        appointment.setDate(appointmentRequestDTO.getDate());

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

        return appointmentDTO;
    }

    public void updateEntity(Appointment appointment, AppointmentRequestDTO dto) {
       if (appointment.getPatient() != null) {
            appointment.getPatient().setId(dto.getPatientId());
        }

        if (appointment.getDentist() != null) {
            appointment.getDentist().setId(dto.getDentistId());
        }

        appointment.setDate(dto.getDate());
    }
}
