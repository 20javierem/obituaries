package com.moreno.obituaries.data.models;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "obituary_tbl")
public class Obituary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Date created;
    @UpdateTimestamp
    private Date updated;
    @ManyToOne
    private Conciliator conciliator;
    @ManyToOne
    private Person part1;
    @ManyToOne
    private Person part2;
    @OneToMany(mappedBy = "obituary")
    private List<Matter> matters=new ArrayList<>();

}
