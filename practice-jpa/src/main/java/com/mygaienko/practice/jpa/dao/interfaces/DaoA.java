package com.mygaienko.practice.jpa.dao.interfaces;

import com.mygaienko.practice.jpa.model.BeanA;
import org.springframework.stereotype.Repository;

/**
 * Created by mygadmy on 03/12/15.
 */
@Repository
public interface DaoA {

    BeanA findAById(long id);

}
