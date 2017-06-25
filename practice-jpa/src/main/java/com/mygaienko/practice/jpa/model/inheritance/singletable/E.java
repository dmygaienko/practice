package com.mygaienko.practice.jpa.model.inheritance.singletable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by enda1n on 25.06.2017.
 */
@Entity
@DiscriminatorValue("ETYPE")
public class E extends SingleSuperClass {

    private String e1;

    public String getE1() {
        return e1;
    }

    public void setE1(String e1) {
        this.e1 = e1;
    }

    @Override
    public String toString() {
        return "E{" +
                "e1='" + e1 + '\'' +
                '}';
    }
}
