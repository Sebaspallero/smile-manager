package com.smilemanager.smile_manager.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smilemanager.smile_manager.DTO.appointment.AppointmentResponseDTO;
import com.smilemanager.smile_manager.model.Appointment;
import com.smilemanager.smile_manager.model.Dentist;
import com.smilemanager.smile_manager.model.DentistAvailability;
import com.smilemanager.smile_manager.model.Patient;

@Component
public class AppointmentMapper {

    private DentistMapper dentistMapper;
    private PatientMapper patientMapper;    

    @Autowired
    public AppointmentMapper(DentistMapper dentistMapper, PatientMapper patientMapper) {
        this.dentistMapper = dentistMapper;
        this.patientMapper = patientMapper;
    }

    public Appointment toEntity(Patient patient, DentistAvailability availability) {
        Appointment appointment = new Appointment();
       
        appointment.setPatient(patient);
        appointment.setDentist(availability.getDentist());
        appointment.setDate(availability.getDate());
        appointment.setTime(availability.getTime());

        return appointment;
    }

    public AppointmentResponseDTO toDTO(Appointment appointment, Dentist dentist, Patient patient) {
        if (appointment == null) {
            return null;
            
        }

        AppointmentResponseDTO appointmentDTO = new AppointmentResponseDTO();

        appointmentDTO.setId(appointment.getId());
        appointmentDTO.setPatient(patientMapper.toDTO(patient));
        appointmentDTO.setDentist(dentistMapper.toDTO(dentist));
        appointmentDTO.setDate(appointment.getDate());
        appointmentDTO.setTime(appointment.getTime());

        return appointmentDTO;
    }

    public void updateEntity(Appointment appointment, AppointmentResponseDTO dto) {
        appointment.setDate(dto.getDate());
        appointment.setTime(dto.getTime());
    }
}
