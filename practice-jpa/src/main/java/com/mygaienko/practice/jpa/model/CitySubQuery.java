package com.mygaienko.practice.jpa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mygaienko.practice.jpa.model.converter.CityEnumConverter;
import com.mygaienko.practice.jpa.model.listener.CityListener;
import org.hibernate.annotations.Subselect;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mygadmy on 03/12/15.
 */
@Entity
@Subselect(
        "select c.id, c.name, c.city_enum " +
                "from CITY c" +
                "join COUNTRY ctry on ctry.id1 = c.country_id1 and ctry.id2 = c.country_id2"
)
public class CitySubQuery implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column(name = "city_enum")
    @Convert(converter = CityEnumConverter.class)
    private CityEnum cityEnum;

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

    public CityEnum getCityEnum() {
        return cityEnum;
    }

    public void setCityEnum(CityEnum cityEnum) {
        this.cityEnum = cityEnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CitySubQuery city = (CitySubQuery) o;

        if (id != null ? !id.equals(city.id) : city.id != null) return false;
        if (name != null ? !name.equals(city.name) : city.name != null) return false;
        if (countryName != null ? !countryName.equals(city.countryName) : city.countryName != null) return false;
        if (country != null ? !country.equals(city.country) : city.country != null) return false;
        if (cityType != city.cityType) return false;
        return shortInfo != null ? shortInfo.equals(city.shortInfo) : city.shortInfo == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (countryName != null ? countryName.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (cityType != null ? cityType.hashCode() : 0);
        result = 31 * result + (shortInfo != null ? shortInfo.hashCode() : 0);
        return result;
    }
}
