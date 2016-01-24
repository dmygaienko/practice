package com.mygaienko.practice.jpa.service.interfaces;

import com.mygaienko.practice.jpa.model.BeanA;
import org.springframework.stereotype.Service;

/**
 * Created by mygadmy on 03/12/15.
 */
@Service
public interface ServiceA {

    BeanA getAById(long id);

}
