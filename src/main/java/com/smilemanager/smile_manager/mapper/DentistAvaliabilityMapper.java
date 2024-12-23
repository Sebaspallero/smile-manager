package com.smilemanager.smile_manager.mapper;

import org.springframework.stereotype.Component;

import com.smilemanager.smile_manager.DTO.dentistAvaliability.DentistAvaliabilityRequestDTO;
import com.smilemanager.smile_manager.DTO.dentistAvaliability.DentistAvaliabilityResponseDTO;
import com.smilemanager.smile_manager.model.Dentist;
import com.smilemanager.smile_manager.model.DentistAvailability;

@Component
public class DentistAvaliabilityMapper {

    public DentistAvailability toEntity(DentistAvaliabilityRequestDTO dentistAvaliabilityRequestDTO) {
        DentistAvailability dentistAvaliability = new DentistAvailability();

        Dentist dentist = new Dentist();
        dentist.setId(dentistAvaliabilityRequestDTO.getDentistId());
        dentistAvaliability.setDentist(dentist);

        dentistAvaliability.setDate(dentistAvaliabilityRequestDTO.getDate());
        dentistAvaliability.setTime(dentistAvaliabilityRequestDTO.getTime());
        dentistAvaliability.setAvailable(dentistAvaliabilityRequestDTO.isAvailable());

        return dentistAvaliability;
    }

    public DentistAvaliabilityResponseDTO toDTO(DentistAvailability dentistAvaliability) {
        if (dentistAvaliability == null) {
            return null;
            
        }

        DentistAvaliabilityResponseDTO dentistAvaliabilityDTO = new DentistAvaliabilityResponseDTO();

        dentistAvaliabilityDTO.setId(dentistAvaliability.getId());
        dentistAvaliabilityDTO.setDentist(dentistAvaliability.getDentist().getId());
        dentistAvaliabilityDTO.setDate(dentistAvaliability.getDate());
        dentistAvaliabilityDTO.setTime(dentistAvaliability.getTime());
        dentistAvaliabilityDTO.setAvailable(dentistAvaliability.isAvailable());

        return dentistAvaliabilityDTO;
    }

    public void updateEntity(DentistAvailability dentistAvaliability, DentistAvaliabilityRequestDTO dto) {
       if (dentistAvaliability.getDentist() != null) {
            dentistAvaliability.getDentist().setId(dto.getDentistId());
        }

        dentistAvaliability.setDate(dto.getDate());
        dentistAvaliability.setTime(dto.getTime());
        dentistAvaliability.setAvailable(dto.isAvailable());
    }
}
