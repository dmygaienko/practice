package com.mygaienko.practice.dao;

import com.mygaienko.practice.dao.interfaces.DaoA;
import com.mygaienko.practice.model.BeanA;
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
