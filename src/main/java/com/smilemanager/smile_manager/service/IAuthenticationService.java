package com.smilemanager.smile_manager.service;

import com.smilemanager.smile_manager.DTO.authentication.AuthenticationRequestDTO;
import com.smilemanager.smile_manager.DTO.authentication.AuthenticationResponseDTO;
import com.smilemanager.smile_manager.DTO.authentication.RegisterRequestDTO;

public interface IAuthenticationService {
    public AuthenticationResponseDTO register(RegisterRequestDTO request);
    public AuthenticationResponseDTO login(AuthenticationRequestDTO request);
}
