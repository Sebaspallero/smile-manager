package com.smilemanager.smile_manager.DTO.dentistAvaliability;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;

public class DentistAvaliabilityRequestDTO {

    @NotNull(message = "Dentist ID is mandatory")
    private Long dentistId;

    @NotNull(message = "Date is mandatory")
    private LocalDate date;

    @NotNull(message = "Time is mandatory")
    private LocalTime time;

    @NotNull(message = "Availability is mandatory")
    private boolean isAvailable = true;

    public Long getDentistId() {
        return dentistId;
    }

    public void setDentistId(Long dentistId) {
        this.dentistId = dentistId;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }  
}
