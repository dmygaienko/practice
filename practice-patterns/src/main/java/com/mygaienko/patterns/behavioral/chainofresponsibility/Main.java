package com.mygaienko.patterns.behavioral.chainofresponsibility;

/**
 * Created by dmygaenko on 23/03/2016.
 */
public class Main {

    public static void main(String[] args) {
        AHandler aHandler = new AHandler();

        BHandler bHandler = new BHandler();
        aHandler.setNext(bHandler);


        aHandler.handle(new Context());

    }
}
