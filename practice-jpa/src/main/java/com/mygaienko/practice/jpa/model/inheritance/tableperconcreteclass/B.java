package com.mygaienko.practice.jpa.model.inheritance.tableperconcreteclass;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by enda1n on 25.06.2017.
 */
@Entity
public class B extends SuperClass {

    @Id
    private Long id;

    private String b1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getB1() {
        return b1;
    }

    public void setB1(String b1) {
        this.b1 = b1;
    }
}
