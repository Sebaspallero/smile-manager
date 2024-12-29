package com.smilemanager.smile_manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.smilemanager.smile_manager.model.Role;
import com.smilemanager.smile_manager.model.User;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.smilemanager.smile_manager.DTO.authentication.AuthenticationRequestDTO;
import com.smilemanager.smile_manager.DTO.authentication.AuthenticationResponseDTO;
import com.smilemanager.smile_manager.DTO.authentication.RegisterRequestDTO;
import com.smilemanager.smile_manager.configuration.JwtService;
import com.smilemanager.smile_manager.exception.ResourceNotFoundException;
import com.smilemanager.smile_manager.repository.UserRepository;
import com.smilemanager.smile_manager.service.IAuthenticationService;

@Service
public class AuthenticationService implements IAuthenticationService{

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthenticationResponseDTO register(RegisterRequestDTO request) {
       var user = User.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
            userRepository.save(user);
            var jwt = jwtService.generateToken(user);
            return AuthenticationResponseDTO.builder()
                .jwt(jwt)
                .build();
    }

    @Override
    public AuthenticationResponseDTO login(AuthenticationRequestDTO request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
                
        );

        var user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        var jwt = jwtService.generateToken(user);
            return AuthenticationResponseDTO.builder()
                .jwt(jwt)
                .build();
    }

}
