package com.mygaienko.patterns.behavioral.strategy.recursive;

/**
 * Created by dmygaenko on 19/05/2016.
 */
class Trust extends TaxPayer<Trust> {
    private boolean nonProfit;

    public Trust(long income, boolean nonProfit, TaxStrategy<Trust> strategy) {
        super(income, strategy);
        this.nonProfit = nonProfit;
    }

    protected Trust getThis() {
        return this;
    }

    public boolean isNonProfit() {
        return nonProfit;
    }
}
