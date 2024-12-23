package com.smilemanager.smile_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smilemanager.smile_manager.model.Dentist;

@Repository
public interface DentistRepository extends JpaRepository<Dentist, Long> {

    Dentist findByEmail(String email);

    boolean existsByEmail(String email);

}
