package com.mygaienko.practice.jpa.web;

import com.mygaienko.practice.jpa.model.BeanA;
import com.mygaienko.practice.jpa.service.interfaces.ServiceA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mygadmy on 03/12/15.
 */
@RestController
public class HelloRestController {

    @Autowired
    @Qualifier("serviceA1")
    private ServiceA serviceA;

    @RequestMapping("/rest")
    BeanA restHello() {
        return serviceA.getAById(2);
    }

}
