package com.smilemanager.smile_manager.DTO.appointment;

import jakarta.validation.constraints.NotNull;

public class AppointmentRequestDTO {
   
    @NotNull(message = "Availability id is mandatory")
    private Long availabilityId;

    @NotNull(message = "Patient id is mandatory")
    private Long patientId;

    public Long getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(Long availabilityId) {
        this.availabilityId = availabilityId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }  
}
