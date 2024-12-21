package com.smilemanager.smile_manager.mapper;

import org.springframework.stereotype.Component;

import com.smilemanager.smile_manager.DTO.dentist.DentistRequestDTO;
import com.smilemanager.smile_manager.DTO.dentist.DentistResponseDTO;
import com.smilemanager.smile_manager.model.Dentist;

@Component
public class DentistMapper {
    
    public Dentist toEntity(DentistRequestDTO dentistRequestDTO) {
        Dentist dentist = new Dentist();
        dentist.setName(dentistRequestDTO.getName());
        dentist.setLastName(dentistRequestDTO.getLastName());
        dentist.setEmail(dentistRequestDTO.getEmail());
        dentist.setBirthDate(dentistRequestDTO.getBirthDate());
        dentist.setSpecialty(dentistRequestDTO.getSpecialty());
        dentist.setInsurance(dentistRequestDTO.getInsurance());
        return dentist;
    }

    public DentistResponseDTO toDTO(Dentist dentist) {
        DentistResponseDTO dentistDTO = new DentistResponseDTO();
        dentistDTO.setId(dentist.getId());
        dentistDTO.setName(dentist.getName());
        dentistDTO.setLastName(dentist.getLastName());
        dentistDTO.setEmail(dentist.getEmail());
        dentistDTO.setBirthDate(dentist.getBirthDate());
        dentistDTO.setSpecialty(dentist.getSpecialty());
        dentistDTO.setInsurance(dentist.getInsurance());
        return dentistDTO;
    }

    public void updateEntity(Dentist dentist, DentistRequestDTO dto) {
        dentist.setName(dto.getName());
        dentist.setLastName(dto.getLastName());
        dentist.setEmail(dto.getEmail());
        dentist.setBirthDate(dto.getBirthDate());
        dentist.setSpecialty(dto.getSpecialty());
        dentist.setInsurance(dto.getInsurance());
    }
}
