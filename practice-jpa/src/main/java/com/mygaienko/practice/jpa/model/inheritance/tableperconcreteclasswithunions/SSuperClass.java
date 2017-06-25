package com.mygaienko.practice.jpa.model.inheritance.tableperconcreteclasswithunions;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Created by enda1n on 25.06.2017.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class SSuperClass {

    @Id
    private Long id;

    private String s1;

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    @Override
    public String toString() {
        return "SSuperClass{" +
                "s1='" + s1 + '\'' +
                '}';
    }
}


