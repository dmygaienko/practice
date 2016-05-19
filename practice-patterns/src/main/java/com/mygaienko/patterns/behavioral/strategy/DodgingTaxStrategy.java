package com.mygaienko.patterns.behavioral.strategy;

/**
 * Created by dmygaenko on 19/05/2016.
 */
class DodgingTaxStrategy<P extends TaxPayer> implements TaxStrategy<P> {

    public long computeTax(P payer) {
        return 0;
    }

}
