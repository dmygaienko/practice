package com.mygaienko.patterns.behavioral.strategy.recursive;

/**
 * Created by dmygaenko on 19/05/2016.
 */
class DodgingTaxStrategy<P extends TaxPayer<P>> implements TaxStrategy<P> {
    public long computeTax(P payer) {
        return 0;
    }
}
