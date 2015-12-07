package com.mygaienko.practice.dao;


import com.mygaienko.practice.dao.interfaces.DaoA;
import com.mygaienko.practice.model.BeanA;

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
