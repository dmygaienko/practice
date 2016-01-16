package com.mygaienko.practice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by mygadmy on 03/12/15.
 */
@Entity
public class BeanA {

    @Id
    @GeneratedValue
    private long id;
    @Column
    private String name;

    public BeanA(long id) {
        this.id = id;
    }

    public BeanA(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
