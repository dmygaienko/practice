package com.mygaienko.patterns.behavioral.strategy.recursive;

/**
 * Created by dmygaenko on 19/05/2016.
 */
abstract class TaxPayer<P extends TaxPayer<P>> {
    public long income;
    private TaxStrategy<P> strategy;

    public TaxPayer(long income, TaxStrategy<P> strategy) {
        this.income = income;
        this.strategy = strategy;
    }

    protected abstract P getThis();

    public long getIncome() {
        return income;
    }

    public long computeTax() {
        return strategy.computeTax(getThis());
    }
}
