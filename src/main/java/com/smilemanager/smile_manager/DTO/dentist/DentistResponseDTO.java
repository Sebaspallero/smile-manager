package com.smilemanager.smile_manager.DTO.dentist;

import java.time.LocalDate;
import java.util.List;

public class DentistResponseDTO {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private List <String> specialty;
    private List <String> insurance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List <String> getSpecialty() {
        return specialty;
    }

    public void setSpecialty(List <String> specialty) {
        this.specialty = specialty;
    }

    public List <String> getInsurance() {
        return insurance;
    }

    public void setInsurance(List <String> insurance) {
        this.insurance = insurance;
    }
}
