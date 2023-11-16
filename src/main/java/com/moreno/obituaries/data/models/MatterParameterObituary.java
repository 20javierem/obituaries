package com.moreno.obituaries.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity(name = "matter_parameter_obituary_tbl")
public class MatterParameterObituary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Date created;
    @UpdateTimestamp
    private Date updated;
    @ManyToOne
    private MatterParameter matterParameter;
    @NotBlank(message = "Valor")
    private String value;

    public MatterParameter getMatterParameter() {
        return matterParameter;
    }

    public void setMatterParameter(MatterParameter matterParameter) {
        this.matterParameter = matterParameter;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
