package com.mygaienko.practice.jpa.model.inheritance.tableperconcreteclass;

import javax.persistence.MappedSuperclass;

/**
 * Created by enda1n on 25.06.2017.
 */
@MappedSuperclass
public class SuperClass {

    private String s1;

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    @Override
    public String toString() {
        return "SuperClass{" +
                "s1='" + s1 + '\'' +
                '}';
    }
}


