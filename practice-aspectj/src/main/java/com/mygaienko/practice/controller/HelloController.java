package com.mygaienko.practice.controller;

import com.mygaienko.practice.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private HelloService service;

    @RequestMapping(path = "/right", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    String get() {
        return service.execute();
    }

    @RequestMapping(path = "/wrong", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    String getWrong() {
        return service.wrongExecute();
    }
}
