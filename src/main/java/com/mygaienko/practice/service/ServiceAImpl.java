package com.mygaienko.practice.service;

import com.mygaienko.practice.dao.interfaces.DaoA;
import com.mygaienko.practice.model.BeanA;
import com.mygaienko.practice.service.interfaces.ServiceA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by mygadmy on 03/12/15.
 */
@Service("serviceAImpl")
public class ServiceAImpl implements ServiceA {

    @Autowired
    @Qualifier("daoAImpl")
    private DaoA daoA;


    @Override
    public BeanA getAById(long id) {
        return daoA.findAById(id);
    }



}
