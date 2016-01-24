package com.mygaienko.practice.jpa.web;

import com.mygaienko.practice.jpa.service.interfaces.ServiceA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mygadmy on 03/12/15.
 */
@Controller
public class HelloController {

    @Autowired
    @Qualifier("serviceAImpl")
    private ServiceA serviceA;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World! " + serviceA.getAById(1).getId();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HelloController.class, args);
    }
}
