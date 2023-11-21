package com.moreno.obituaries.data.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TypeMatter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Date created;
    @UpdateTimestamp
    private Date updated;
    @NotBlank(message = "Nombre")
    private String name;
    @NotBlank(message = "Contenido")
    private String content;
    @OneToMany(mappedBy = "matter")
    private List<TypeMatterParameter> parameters=new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<TypeMatterParameter> getParameters() {
        return parameters;
    }
}
