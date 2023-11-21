package com.moreno.obituaries.data.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "matter_obituary_tbl")
public class Matter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Date created;
    @UpdateTimestamp
    private Date updated;
    @ManyToOne
    private Obituary obituary;
    @ManyToOne
    private TypeMatter typeMatter;
    @OneToMany(mappedBy = "matterObituary")
    private List<MatterParameter> matterParameterObituaries=new ArrayList<>();

    public Obituary getObituary() {
        return obituary;
    }

    public void setObituary(Obituary obituary) {
        this.obituary = obituary;
    }

    public TypeMatter getMatter() {
        return typeMatter;
    }

    public void setMatter(TypeMatter typeMatter) {
        this.typeMatter = typeMatter;
    }

    public List<MatterParameter> getMatterParameterObituaries() {
        return matterParameterObituaries;
    }

    public String getStringMatter(){
        String matterString= typeMatter.getContent();
        for (MatterParameter matterParameter : matterParameterObituaries) {
            matterString=matterString.replace(matterParameter.getMatterParameter().getName(), matterParameter.getValue());
        }
        return matterString;
    }
}
