package com.moreno.obituaries.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
@Entity(name = "record_number_tbl")
public class RecordNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Date created;
    @UpdateTimestamp
    private Date updated;
    @ManyToOne
    @NotNull(message = "Tipo de Registro")
    private TypeRecordNumber typeRecordNumber;
    @NotBlank(message = "Número")
    private String number;
    @NotNull(message = "Conciliador")
    @ManyToOne
    private Conciliator conciliator;
}
