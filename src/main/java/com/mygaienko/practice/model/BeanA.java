package com.mygaienko.practice.model;

/**
 * Created by mygadmy on 03/12/15.
 */
public class BeanA {

    private long id;
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
