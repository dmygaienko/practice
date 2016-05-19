package com.mygaienko.patterns.behavioral.strategy;

/**
 * Created by dmygaenko on 19/05/2016.
 */
abstract class TaxPayer {
    public long income; // in cents
    public TaxPayer(long income) { this.income = income; }
    public long getIncome() { return income; }
}
