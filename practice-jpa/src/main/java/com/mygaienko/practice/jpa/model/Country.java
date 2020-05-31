package com.mygaienko.practice.jpa.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by liuda on 12/6/2015.
 */

@Entity
@NamedNativeQuery(
        name = "getAllCountriesNamedQuery",
        query = "select * FROM COUNTRY",
        resultClass = Country.class
)
public class Country implements Serializable{

    @EmbeddedId
    private CountryId id;

    @Column
    private String name;

    @Embedded
    private ZipCode code;

    // when remove from collection and persist city also will be deleted on database
    @JsonManagedReference
    @OneToMany(mappedBy = "country", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<City> cities;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "country", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<CitySubQuery> cities1;

    @Embedded
    private CitiesWrapper citiesWrapper;

    public Country() {
    }

    public Country(CountryId id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<CitySubQuery> getCities1() {
        return cities1;
    }

    public void setCities1(List<CitySubQuery> cities1) {
        this.cities1 = cities1;
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
        for (City city : cities) {
            city.setCountry(this);
        }
        this.cities = cities;
    }

    public CitiesWrapper getCitiesWrapper() {
        return citiesWrapper;
    }

    public void setCitiesWrapper(CitiesWrapper citiesWrapper) {
        this.citiesWrapper = citiesWrapper;
    }
}
