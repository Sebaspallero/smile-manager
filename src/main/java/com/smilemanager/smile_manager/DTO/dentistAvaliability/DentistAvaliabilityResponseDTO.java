package com.smilemanager.smile_manager.DTO.dentistAvaliability;

import java.time.LocalDate;
import java.time.LocalTime;

public class DentistAvaliabilityResponseDTO {
    
    private Long id;
    private Long dentist;
    private LocalDate date;
    private LocalTime time;
    private boolean isAvailable;
    
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
