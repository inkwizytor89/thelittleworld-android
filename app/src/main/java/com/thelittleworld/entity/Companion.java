package com.thelittleworld.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

@Entity(
        nameInDb = "companions"
)
public class Companion {

    @Id
    public Long id;

    public String name;

    public Double fury;

    public Double sociality;

    public String sex;

    public Double strength;

    public Double agility;

    public Double stamina;

    public Double intelligence;

    public Double wisdom;

    public Double charisma;

    public Date updated;

    public Date created;

    @Generated(hash = 1296329680)
    public Companion(Long id, String name, Double fury, Double sociality,
                     String sex, Double strength, Double agility, Double stamina,
                     Double intelligence, Double wisdom, Double charisma, Date updated,
                     Date created) {
        this.id = id;
        this.name = name;
        this.fury = fury;
        this.sociality = sociality;
        this.sex = sex;
        this.strength = strength;
        this.agility = agility;
        this.stamina = stamina;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
        this.updated = updated;
        this.created = created;
    }

    @Generated(hash = 1351821811)
    public Companion() {
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

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Double getStrength() {
        return this.strength;
    }

    public void setStrength(Double strength) {
        this.strength = strength;
    }

    public Double getAgility() {
        return this.agility;
    }

    public void setAgility(Double agility) {
        this.agility = agility;
    }

    public Double getStamina() {
        return this.stamina;
    }

    public void setStamina(Double stamina) {
        this.stamina = stamina;
    }

    public Double getIntelligence() {
        return this.intelligence;
    }

    public void setIntelligence(Double intelligence) {
        this.intelligence = intelligence;
    }

    public Double getWisdom() {
        return this.wisdom;
    }

    public void setWisdom(Double wisdom) {
        this.wisdom = wisdom;
    }

    public Double getCharisma() {
        return this.charisma;
    }

    public void setCharisma(Double charisma) {
        this.charisma = charisma;
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
