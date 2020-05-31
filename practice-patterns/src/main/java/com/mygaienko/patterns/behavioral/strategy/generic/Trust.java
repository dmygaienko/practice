package com.mygaienko.patterns.behavioral.strategy.generic;


/**
 * Created by dmygaenko on 19/05/2016.
 */
public class Trust extends TaxPayer<Trust> {

    private boolean nonProfit;

    public Trust(long income, boolean nonProfit) {
        super(income);
        this.nonProfit = nonProfit;
    }



    public boolean isNonProfit() {
        return nonProfit;
    }

    @Override
    protected Trust getThis() {
        return null;
    }
}
