package com.smilemanager.smile_manager.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "dentists")
public class Dentist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 50)
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NotBlank(message = "Last name is mandatory")
    @Size(min = 3, max = 50)
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Email(message = "Email is mandatory")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    @Past
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "dentist_specialty", joinColumns = @JoinColumn(name = "dentist_id"))
    @Column(name = "specialty", nullable = false, length = 100)
    private List <String> specialty;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "dentist_insurance", joinColumns = @JoinColumn(name = "dentist_id"))
    @Column(name = "insurance", nullable = false, length = 100)
    private List <String> insurance;

    @NotNull
    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate = LocalDateTime.now();

    public Dentist() {
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

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    
}
