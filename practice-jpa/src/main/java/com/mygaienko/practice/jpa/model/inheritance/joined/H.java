package com.mygaienko.practice.jpa.model.inheritance.joined;

import javax.persistence.Entity;

/**
 * Created by enda1n on 25.06.2017.
 */
@Entity
public class H extends JoinedSuperClass {

    private String h1;

    public String getH1() {
        return h1;
    }

    public void setH1(String h1) {
        this.h1 = h1;
    }

    @Override
    public String toString() {
        return "H{" +
                "h1='" + h1 + '\'' +
                "} " + super.toString();
    }
}
