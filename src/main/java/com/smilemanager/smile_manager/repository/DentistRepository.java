package com.smilemanager.smile_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smilemanager.smile_manager.model.Dentist;

public interface DentistRepository extends JpaRepository<Dentist, Long> {

}
