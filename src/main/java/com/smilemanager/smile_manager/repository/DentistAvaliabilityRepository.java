package com.smilemanager.smile_manager.repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smilemanager.smile_manager.model.DentistAvailability;

@Repository
public interface DentistAvaliabilityRepository extends JpaRepository<DentistAvailability, Long>{
    boolean existsByDentistIdAndDateAndTime(Long dentistId, LocalDate date, LocalTime time);

}
