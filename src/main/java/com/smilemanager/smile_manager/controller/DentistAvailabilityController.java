package com.smilemanager.smile_manager.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smilemanager.smile_manager.DTO.dentistAvaliability.DentistAvaliabilityResponseDTO;
import com.smilemanager.smile_manager.service.IDentistAvailabilityService;


@RestController
@RequestMapping("/dentist/availability")
public class DentistAvailabilityController {

    private IDentistAvailabilityService dentistAvailabilityService;

    @Autowired
    public DentistAvailabilityController(IDentistAvailabilityService dentistAvailabilityService) {
        this.dentistAvailabilityService = dentistAvailabilityService;
    }

    @PostMapping("/{dentistId}")
    public ResponseEntity<DentistAvaliabilityResponseDTO> createAvailability(
        @PathVariable Long dentistId, 
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime time) {
                    
        DentistAvaliabilityResponseDTO availability = dentistAvailabilityService.createAvailability(dentistId, date, time);
        return ResponseEntity.status(HttpStatus.CREATED).body(availability);
    }

    @GetMapping
    public ResponseEntity <List<DentistAvaliabilityResponseDTO>> findAllAvaliabilities(){
        List <DentistAvaliabilityResponseDTO> availabilities = dentistAvailabilityService.findAllAvaliabilities();
        return ResponseEntity.status(HttpStatus.OK).body(availabilities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DentistAvaliabilityResponseDTO> findById(@PathVariable Long id){
        DentistAvaliabilityResponseDTO availability = dentistAvailabilityService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(availability);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvailability(@PathVariable Long id){
        dentistAvailabilityService.deleteAvailability(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
