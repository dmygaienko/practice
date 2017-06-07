package com.mygaienko.practice.jpa.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

/**
 * Created by liuda on 12/6/2015.
 */

@Embeddable
public class CitiesWrapper implements Serializable {

    // when remove from collection and persist city also will be deleted on database
    @JsonManagedReference
    @OneToMany(mappedBy = "country", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<City> cities;

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
