package com.mygaienko.practice.dao.interfaces;

import com.mygaienko.practice.model.BeanA;
import org.springframework.stereotype.Repository;

/**
 * Created by mygadmy on 03/12/15.
 */
@Repository
public interface DaoA {

    BeanA findAById(long id);

}
