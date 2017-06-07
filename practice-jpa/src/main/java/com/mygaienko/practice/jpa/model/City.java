package com.mygaienko.practice.jpa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mygaienko.practice.jpa.model.listener.CityListener;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mygadmy on 03/12/15.
 */
@Entity
@EntityListeners(CityListener.class)
public class City implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column(name = "country_name")
    private String countryName;

    @JsonBackReference
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "country_id1", referencedColumnName = "id1"),
            @JoinColumn(name = "country_id2", referencedColumnName = "id2")})
    private Country country;

    @Enumerated
    @Column(name = "city_type")
    private CityType cityType;

    @Transient
    private String shortInfo;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public CityType getCityType() {
        return cityType;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setCityType(CityType cityType) {
        this.cityType = cityType;
    }

    public String getShortInfo() {
        if (StringUtils.isEmpty(shortInfo)) {
            shortInfo = id + ": " + name + " - " + countryName;
        }
        return shortInfo;
    }

    public Country getCountry() {
        return country;
    }
}
