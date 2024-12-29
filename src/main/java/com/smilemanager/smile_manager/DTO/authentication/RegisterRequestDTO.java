package com.smilemanager.smile_manager.DTO.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {
    
    private String name;
    private String lastName;
    private String email;
    private String password;

    
}
