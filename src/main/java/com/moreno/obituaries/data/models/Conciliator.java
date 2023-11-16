package com.moreno.obituaries.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity(name = "conciliator_tbl")
public class Conciliator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Date created;
    @UpdateTimestamp
    private Date updated;
    @OneToOne
    @NotNull(message = "Persona")
    private Person person;
    @NotBlank(message = "Número de registro")
    private String registryNumber;
    @NotBlank(message = "Npumero de registro de especialización en familia")
    private String familySpecializationRegistration;

}
