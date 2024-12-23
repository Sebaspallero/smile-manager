package com.smilemanager.smile_manager.service;

import com.smilemanager.smile_manager.model.Appointment;
import com.smilemanager.smile_manager.model.Dentist;

public interface IEmailService {
    public void sendHtmlMessage(String to, String subject, String htmlContent);
    public String createAppointmentEmailBody(Appointment appointment, Dentist dentist);
    public String getAppointmentEmailSubject();
}   
