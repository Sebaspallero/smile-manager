package com.smilemanager.smile_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smilemanager.smile_manager.DTO.authentication.AuthenticationRequestDTO;
import com.smilemanager.smile_manager.DTO.authentication.AuthenticationResponseDTO;
import com.smilemanager.smile_manager.DTO.authentication.RegisterRequestDTO;
import com.smilemanager.smile_manager.service.impl.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity <AuthenticationResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity <AuthenticationResponseDTO> login(@RequestBody AuthenticationRequestDTO request) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.login(request));
    }
}
