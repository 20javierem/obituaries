package com.moreno.obituaries.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity(name = "matter_parameter_obituary_tbl")
public class MatterParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Date created;
    @UpdateTimestamp
    private Date updated;
    @ManyToOne
    private TypeMatterParameter typeMatterParameter;
    @NotBlank(message = "Valor")
    private String value;

    public TypeMatterParameter getMatterParameter() {
        return typeMatterParameter;
    }

    public void setMatterParameter(TypeMatterParameter typeMatterParameter) {
        this.typeMatterParameter = typeMatterParameter;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
