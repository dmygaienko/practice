package com.mygaienko.patterns.behavioral.strategy.generic;

/**
 * Created by dmygaenko on 19/05/2016.
 */
public class Person extends TaxPayer<Person> {

    public Person(long income) {
        super(income);
    }

    @Override
    protected Person getThis() {
        return this;
    }
}
