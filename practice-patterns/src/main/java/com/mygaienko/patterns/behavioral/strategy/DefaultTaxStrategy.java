package com.mygaienko.patterns.behavioral.strategy;

/**
 * Created by dmygaenko on 19/05/2016.
 */
class DefaultTaxStrategy<P extends TaxPayer> implements TaxStrategy<P> {
    private static final double RATE = 0.40;

    public long computeTax(P payer) {
        return Math.round(payer.getIncome() * RATE);
    }
}
