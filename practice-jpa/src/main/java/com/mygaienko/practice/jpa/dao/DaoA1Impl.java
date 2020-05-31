package com.mygaienko.practice.jpa.dao;


import com.mygaienko.practice.jpa.dao.interfaces.DaoA;
import com.mygaienko.practice.jpa.model.BeanA;

/**
 * Created by mygadmy on 03/12/15.
 */
public class DaoA1Impl implements DaoA {

    private String property;

    @Override
    public BeanA findAById(long id) {
        return new BeanA(id, property);
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
