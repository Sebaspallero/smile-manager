package com.smilemanager.smile_manager.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(name = "last_name",nullable = false, length = 50)
    private String lastName;

    @Email
    @NotBlank
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    @Past
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @NotNull
    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate = LocalDateTime.now();
    

    public Patient() {
    }


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


    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }


    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
