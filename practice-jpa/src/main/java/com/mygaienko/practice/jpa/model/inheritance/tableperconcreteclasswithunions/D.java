package com.mygaienko.practice.jpa.model.inheritance.tableperconcreteclasswithunions;

import javax.persistence.Entity;

/**
 * Created by enda1n on 25.06.2017.
 */
@Entity
public class D extends SSuperClass {

    private String b1;

    public String getB1() {
        return b1;
    }

    public void setB1(String b1) {
        this.b1 = b1;
    }

    @Override
    public String toString() {
        return "D{" +
                "b1='" + b1 + '\'' +
                "} " + super.toString();
    }
}
