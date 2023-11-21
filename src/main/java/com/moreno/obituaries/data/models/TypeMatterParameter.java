package com.moreno.obituaries.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity(name = "matter_parameter_tbl")
public class TypeMatterParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Date created;
    @UpdateTimestamp
    private Date updated;
    @NotNull
    private TypeMatter typeMatter;
    @NotBlank(message = "Nombre")
    private String name;
    @NotBlank(message = "Tipo")
    private String type; //String, number, date

    public TypeMatter getMatter() {
        return typeMatter;
    }

    public void setMatter(TypeMatter typeMatter) {
        this.typeMatter = typeMatter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
