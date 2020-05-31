package com.mygaienko.patterns.behavioral.strategy.recursive;

/**
 * Created by dmygaenko on 19/05/2016.
 */
public class Main {

    public static void main(String[] args) {
        Person person = new Person(100, new DodgingTaxStrategy());
        person.computeTax();
    }
}
