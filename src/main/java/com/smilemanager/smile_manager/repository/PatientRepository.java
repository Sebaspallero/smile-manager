package com.smilemanager.smile_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smilemanager.smile_manager.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
