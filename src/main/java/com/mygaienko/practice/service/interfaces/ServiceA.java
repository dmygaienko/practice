package com.mygaienko.practice.service.interfaces;

import com.mygaienko.practice.model.BeanA;
import org.springframework.stereotype.Service;

/**
 * Created by mygadmy on 03/12/15.
 */
@Service
public interface ServiceA {

    BeanA getAById(long id);

}
