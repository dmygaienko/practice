package com.mygaienko.practice.jpa.model.inheritance.tableperconcreteclass;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by enda1n on 25.06.2017.
 */
@Entity
public class A extends SuperClass {

    @Id
    private Long id;

    private String a1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    @Override
    public String toString() {
        return "A{" +
                "id=" + id +
                ", a1='" + a1 + '\'' +
                "} " + super.toString();
    }
}
