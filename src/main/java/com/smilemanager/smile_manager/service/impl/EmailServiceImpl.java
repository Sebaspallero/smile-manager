package com.smilemanager.smile_manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.smilemanager.smile_manager.exception.EmailSendException;
import com.smilemanager.smile_manager.model.Appointment;
import com.smilemanager.smile_manager.model.Dentist;
import com.smilemanager.smile_manager.service.IEmailService;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements IEmailService {

    private JavaMailSender mailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public String createAppointmentEmailBody(Appointment appointment, Dentist dentist) {
        String htmlBody = "<html>" +
                          "<body style='font-family: Arial, sans-serif;'>" +
                          "<h2 style='color: #4CAF50;'>Appointment Confirmation</h2>" +
                          "<p>Your appointment has been scheduled for <strong>" + appointment.getDate() + "</strong> with Dr. " + dentist.getLastName() + ".</p>" +
                          "<p>Thank you for choosing our service!</p>" +
                          "<br>" +
                          "<p>Best regards,</p>" +
                          "<p><em>Smile Manager</p>" +
                          "</body>" +
                          "</html>";
        return htmlBody;
    }

    @Override
    public String getAppointmentEmailSubject() {
        return "Appointment Confirmation";
    }

    @Override
    public void sendHtmlMessage(String to, String subject, String htmlContent) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            mailSender.send(message);
            
        } catch (Exception e) {
            throw new EmailSendException("Failed to send HTML email to: " + to, e);
        }
    }

}
