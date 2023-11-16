package com.moreno.obituaries.data.models;

import com.moreno.obituaries.core.Hibernate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
@Entity(name = "user_tbl")
public class User extends Hibernate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Date created;
    @UpdateTimestamp
    private Date updated;
    @NotNull
    @OneToOne
    private Person person;
}
