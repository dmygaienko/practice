package com.mygaienko.practice.jpa.model.inheritance.joined;

import javax.persistence.Entity;

/**
 * Created by enda1n on 25.06.2017.
 */
@Entity
public class G extends JoinedSuperClass {

    private String g1;

    public String getG1() {
        return g1;
    }

    public void setG1(String g1) {
        this.g1 = g1;
    }

    @Override
    public String toString() {
        return "G{" +
                "g1='" + g1 + '\'' +
                "} " + super.toString();
    }
}
