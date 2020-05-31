package com.mygaienko.practice.jpa.dao;

import com.mygaienko.practice.jpa.dao.interfaces.DaoA;
import com.mygaienko.practice.jpa.model.BeanA;
import org.springframework.stereotype.Repository;

/**
 * Created by mygadmy on 03/12/15.
 */
@Repository("daoAImpl")
public class DaoAImpl implements DaoA {

    @Override
    public BeanA findAById(long id) {
        return new BeanA(id);
    }
}
