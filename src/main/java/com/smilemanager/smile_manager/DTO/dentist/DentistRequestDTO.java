package com.smilemanager.smile_manager.DTO.dentist;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class DentistRequestDTO {

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank(message = "Last name is mandatory")
    @Size(min = 3, max = 50)
    private String lastName;

    @Email(message = "Email is mandatory")
    private String email;

    @NotNull
    @Past
    private LocalDate birthDate;

    @NotEmpty(message = "Specialty is mandatory")
    private List <String> specialty;

    @NotEmpty(message = "insurance is mandatory")
    private List <String> insurance;


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

    public List<String> getSpecialty() {
        return specialty;
    }

    public void setSpecialty(List<String> specialty) {
        this.specialty = specialty;
    }

    public List<String> getInsurance() {
        return insurance;
    }

    public void setInsurance(List<String> insurance) {
        this.insurance = insurance;
    }

    
}
