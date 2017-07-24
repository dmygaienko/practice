package com.mygaienko.practice.service;

import org.springframework.stereotype.Service;

/**
 * Created by enda1n on 24.07.2017.
 */
@Service
public class HelloService {

    public String execute() {
        return "Executed";
    }

    public String wrongExecute() {
        throw new IllegalStateException();
    }
}
