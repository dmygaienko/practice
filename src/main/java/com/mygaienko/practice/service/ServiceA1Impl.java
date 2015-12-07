package com.mygaienko.practice.service;

import com.mygaienko.practice.dao.interfaces.DaoA;
import com.mygaienko.practice.model.BeanA;
import com.mygaienko.practice.service.interfaces.ServiceA;

/**
 * Created by mygadmy on 03/12/15.
 */
public class ServiceA1Impl implements ServiceA {

    private DaoA daoA;

    public ServiceA1Impl(DaoA daoA) {
        this.daoA = daoA;
    }

    @Override
    public BeanA getAById(long id) {
        return daoA.findAById(id);
    }

}
