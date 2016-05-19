package com.mygaienko.patterns.behavioral.strategy.recursive;

/**
 * Created by dmygaenko on 19/05/2016.
 */
class Person extends TaxPayer<Person> {
    public Person(long income, TaxStrategy<Person> strategy) {
        super(income, strategy);
    }

    protected Person getThis() {
        return this;
    }
}
