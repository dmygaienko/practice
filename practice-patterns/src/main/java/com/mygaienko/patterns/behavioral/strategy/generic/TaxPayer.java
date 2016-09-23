package com.mygaienko.patterns.behavioral.strategy.generic;

import com.mygaienko.patterns.behavioral.strategy.generic.strategies.TaxStrategy;

/**
 * Created by dmygaenko on 19/05/2016.
 */
public abstract class TaxPayer<P extends TaxPayer<P>> {

    private TaxStrategy<P> strategy;

    public long income; // in cents

    public TaxPayer(long income) {
        this.income = income;
    }

    public long getIncome() {
        return income;
    }

    protected abstract P getThis();

    public long computeTax() {
        return strategy.computeTax(getThis());
    }
}
