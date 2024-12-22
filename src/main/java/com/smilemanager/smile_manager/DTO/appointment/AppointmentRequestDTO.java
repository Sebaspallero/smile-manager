package com.smilemanager.smile_manager.DTO.appointment;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class AppointmentRequestDTO {
   
    @NotNull(message = "Dentist id is mandatory")
    private Long dentistId;

    @NotNull(message = "Patient id is mandatory")
    private Long patientId;

    @NotNull(message = "Date is mandatory")
    private LocalDate date;

    public Long getDentistId() {
        return dentistId;
    }

    public void setDentistId(Long dentistId) {
        this.dentistId = dentistId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
