package com.mygaienko.patterns.behavioral.strategy;

/**
 * Created by dmygaenko on 19/05/2016.
 */
class TrustTaxStrategy extends DefaultTaxStrategy<Trust> {

    public long computeTax(Trust trust) {
        return trust.isNonProfit() ? 0 : super.computeTax(trust);
    }

}
