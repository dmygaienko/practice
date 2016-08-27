package com.mygaienko.common.model.catdog_generics;

/**
 * Created by dmygaenko on 31/05/2016.
 */
public class Client {

    public <T extends Cat & Dog> void use(T t) {

    }
}
