package com.mygaienko.patterns.behavioral.strategy.generic.strategies;


import com.mygaienko.patterns.behavioral.strategy.generic.TaxPayer;

/**
 * Created by dmygaenko on 19/05/2016.
 */
public class DodgingTaxStrategy<P extends TaxPayer<P>> implements TaxStrategy<P> {

    public long computeTax(P payer) {
        return 0;
    }

}
