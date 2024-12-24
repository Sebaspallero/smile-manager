package com.smilemanager.smile_manager.DTO.appointment;

import java.time.LocalDate;
import java.time.LocalTime;

import com.smilemanager.smile_manager.DTO.dentist.DentistResponseDTO;
import com.smilemanager.smile_manager.DTO.patient.PatientResponseDTO;


public class AppointmentResponseDTO {
    
    private Long id;
    private DentistResponseDTO dentist;
    private PatientResponseDTO patient;
    private LocalDate date;
    private LocalTime time;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public DentistResponseDTO getDentist() {
        return dentist;
    }
    public void setDentist(DentistResponseDTO dentist) {
        this.dentist = dentist;
    }
    public PatientResponseDTO getPatient() {
        return patient;
    }
    public void setPatient(PatientResponseDTO patient) {
        this.patient = patient;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public LocalTime getTime() {
        return time;
    }
    public void setTime(LocalTime time) {
        this.time = time;
    }

    
}