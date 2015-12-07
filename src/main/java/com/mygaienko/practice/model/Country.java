package com.mygaienko.practice.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by liuda on 12/6/2015.
 */
@Entity
public class Country implements Serializable{

    @EmbeddedId
    private CountryId id;

    @Column
    private String name;

    @Embedded
    private ZipCode code;

    public Country() {
    }

    public Country(CountryId id, String name) {
        this.id = id;
        this.name = name;
    }

    public CountryId getId() {
        return id;
    }

    public void setId(CountryId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZipCode getCode() {
        return code;
    }

    public void setCode(ZipCode code) {
        this.code = code;
    }
}
