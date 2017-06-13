package com.mygaienko.practice.jpa.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by liuda on 12/6/2015.
 */
@Embeddable
public class CountryId implements Serializable{

    @Column(name = "id1")
    private String id1;

    @Column(name = "id2")
    private String id2;

    public CountryId() {
    }

    public CountryId(String id1, String id2) {
        this.id1 = id1;
        this.id2 = id2;
    }

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountryId countryId = (CountryId) o;

        if (id1 != null ? !id1.equals(countryId.id1) : countryId.id1 != null) return false;
        return id2 != null ? id2.equals(countryId.id2) : countryId.id2 == null;

    }

    @Override
    public int hashCode() {
        int result = id1 != null ? id1.hashCode() : 0;
        result = 31 * result + (id2 != null ? id2.hashCode() : 0);
        return result;
    }
}
