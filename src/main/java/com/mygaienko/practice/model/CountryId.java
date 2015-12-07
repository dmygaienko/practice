package com.mygaienko.practice.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by liuda on 12/6/2015.
 */
@Embeddable
public class CountryId implements Serializable{

    @Column
    private String id1;

    @Column
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
}
