package com.mygaienko.practice.jpa.model.inheritance.tableperconcreteclasswithunions;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by enda1n on 25.06.2017.
 */
@Entity
public class C extends SSuperClass {

    private String a1;

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    @Override
    public String toString() {
        return "C{" +
                "a1='" + a1 + '\'' +
                "} " + super.toString();
    }
}
