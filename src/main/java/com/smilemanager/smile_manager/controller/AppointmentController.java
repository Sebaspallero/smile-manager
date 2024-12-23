package com.smilemanager.smile_manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smilemanager.smile_manager.DTO.appointment.AppointmentRequestDTO;
import com.smilemanager.smile_manager.DTO.appointment.AppointmentResponseDTO;
import com.smilemanager.smile_manager.service.IAppointmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private IAppointmentService appointmentService;

    @Autowired
    public AppointmentController(IAppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity <AppointmentResponseDTO> save(@RequestBody AppointmentRequestDTO appointmentDetails){
        AppointmentResponseDTO appointment = appointmentService.save(appointmentDetails.getAvailabilityId(), appointmentDetails.getPatientId());
        return ResponseEntity.status(HttpStatus.CREATED).body(appointment);
    }

    @GetMapping("/{id}")
    public ResponseEntity <AppointmentResponseDTO> findById(@PathVariable Long id){
        AppointmentResponseDTO appointment = appointmentService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(appointment);
    }

    @GetMapping
    public ResponseEntity <List<AppointmentResponseDTO>> findAll(){
        List <AppointmentResponseDTO> appointmentList = appointmentService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(appointmentList);
    }

    @PutMapping("/{id}")
    public ResponseEntity <AppointmentResponseDTO> update(@PathVariable Long id, @Valid @RequestBody AppointmentRequestDTO appointmentDetails){
        AppointmentResponseDTO appointment = appointmentService.update(id, appointmentDetails);
        return ResponseEntity.status(HttpStatus.OK).body(appointment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> delete(@PathVariable Long id){
        appointmentService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

