package com.mygaienko.patterns.behavioral.strategy;

/**
 * Created by dmygaenko on 23/03/2016.
 */
public class Main {

    public static void main(String[] args) {
        Person person = new Person(10000000);

        TaxStrategy<Person> defaultStrategy = new DefaultTaxStrategy<Person>();
        assert defaultStrategy.computeTax(person) == 4000000;

        TaxStrategy<Person> dodgingStrategy = new DodgingTaxStrategy<Person>();
        assert dodgingStrategy.computeTax(person) == 0;

        TaxStrategy<Trust> trustStrategy = new TrustTaxStrategy();
        Trust nonProfit = new Trust(10000000, true);
        assert trustStrategy.computeTax(nonProfit) == 0;

        Trust forProfit = new Trust(10000000, false);
        assert trustStrategy.computeTax(forProfit) == 4000000;
    }
}
