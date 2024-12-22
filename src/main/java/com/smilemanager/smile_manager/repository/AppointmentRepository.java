package com.smilemanager.smile_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smilemanager.smile_manager.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

}
