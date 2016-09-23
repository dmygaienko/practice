package com.mygaienko.patterns.behavioral.strategy.generic.strategies;


import com.mygaienko.patterns.behavioral.strategy.generic.TaxPayer;

/**
 * Created by dmygaenko on 19/05/2016.
 */
public class DefaultTaxStrategy<P extends TaxPayer<P>> implements TaxStrategy<P> {
    private static final double RATE = 0.40;

    public long computeTax(P payer) {
        return Math.round(payer.getIncome() * RATE);
    }
}
