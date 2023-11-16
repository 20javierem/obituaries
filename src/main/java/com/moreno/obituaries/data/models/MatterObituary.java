package com.moreno.obituaries.data.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "matter_obituary_tbl")
public class MatterObituary {
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
    private Matter matter;
    @OneToMany(mappedBy = "matterObituary")
    private List<MatterParameterObituary> matterParameterObituaries=new ArrayList<>();

    public Obituary getObituary() {
        return obituary;
    }

    public void setObituary(Obituary obituary) {
        this.obituary = obituary;
    }

    public Matter getMatter() {
        return matter;
    }

    public void setMatter(Matter matter) {
        this.matter = matter;
    }

    public List<MatterParameterObituary> getMatterParameterObituaries() {
        return matterParameterObituaries;
    }

    public String getStringMatter(){
        String matterString= matter.getContent();
        for (MatterParameterObituary matterParameterObituary : matterParameterObituaries) {
            matterString=matterString.replace(matterParameterObituary.getMatterParameter().getName(),matterParameterObituary.getValue());
        }
        return matterString;
    }
}
