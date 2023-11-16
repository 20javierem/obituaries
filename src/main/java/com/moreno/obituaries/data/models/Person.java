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

@Entity(name = "person_tbl")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Date created;
    @UpdateTimestamp
    private Date updated;
    @NotBlank(message = "Nombres")
    private String first_name;
    @NotBlank(message = "Apellidos")
    private String last_name;
    @NotBlank(message = "Número de documento")
    private String numberDocument;
    @ManyToOne
    @NotNull(message = "Tipo de documento")
    private TypeDocument typeDocument;
    @NotBlank(message = "Número de celular")
    private String phone;
    @NotEmpty(message = "Direcciones")
    @OneToMany(mappedBy = "person")
    private List<Address> addresses=new ArrayList<>();
}
