package com.moreno.obituaries.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity(name = "company_tbl")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Date created;
    @UpdateTimestamp
    private Date updated;
    @OneToOne
    private Person representative;
    @NotBlank(message = "RUC")
    private String ruc;
    @NotBlank(message = "NOMBRE DE LA EMPRESA")
    private String businessName;

}
