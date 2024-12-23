package com.smilemanager.smile_manager.DTO.appointment;

import java.time.LocalDate;
import java.time.LocalTime;


public class AppointmentResponseDTO {
    
    private Long id;
    private Long dentist;
    private Long patient;
    private LocalDate date;
    private LocalTime time;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getDentist() {
        return dentist;
    }
    public void setDentist(Long dentist) {
        this.dentist = dentist;
    }
    public Long getPatient() {
        return patient;
    }
    public void setPatient(Long patient) {
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
