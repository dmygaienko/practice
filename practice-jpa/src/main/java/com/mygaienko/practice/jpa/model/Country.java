package com.mygaienko.practice.jpa.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by liuda on 12/6/2015.
 */

@Entity
@NamedNativeQuery(
        name = "getAllCountriesNamedQuery",
        query = "select * FROM country",
        resultClass = Country.class
)
public class Country implements Serializable{

    @EmbeddedId
    private CountryId id;

    @Column
    private String name;

    @Embedded
    private ZipCode code;

    @JsonManagedReference
    @OneToMany(mappedBy = "country")
    private List<City> cities;

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

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
