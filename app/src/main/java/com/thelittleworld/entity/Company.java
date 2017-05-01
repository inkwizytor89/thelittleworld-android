package com.thelittleworld.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

@Entity(
        nameInDb = "company"
)
public class Company {

    @Id
    public Long id;

    public String name;

    public Double fury;

    public Double sociality;

    public Date updated;

    public Date created;

    @Generated(hash = 627424302)
    public Company(Long id, String name, Double fury, Double sociality,
                   Date updated, Date created) {
        this.id = id;
        this.name = name;
        this.fury = fury;
        this.sociality = sociality;
        this.updated = updated;
        this.created = created;
    }

    @Generated(hash = 1096856789)
    public Company() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getFury() {
        return this.fury;
    }

    public void setFury(Double fury) {
        this.fury = fury;
    }

    public Double getSociality() {
        return this.sociality;
    }

    public void setSociality(Double sociality) {
        this.sociality = sociality;
    }

    public Date getUpdated() {
        return this.updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
