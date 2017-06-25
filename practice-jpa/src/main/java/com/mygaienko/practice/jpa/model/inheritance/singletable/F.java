package com.mygaienko.practice.jpa.model.inheritance.singletable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by enda1n on 25.06.2017.
 */
@Entity
@DiscriminatorValue("FTYPE")
public class F extends SingleSuperClass {

    private String f1;

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }

    @Override
    public String toString() {
        return "F{" +
                "f1='" + f1 + '\'' +
                '}';
    }
}
