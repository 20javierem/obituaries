package com.moreno.obituaries.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @NotEmpty(message = "Debe tener al menos un número de registro")
    @OneToMany(mappedBy = "conciliator")
    private List<RecordNumber> recordNumbers=new ArrayList<>();

}
