package com.mygaienko.patterns.behavioral.strategy.generic.strategies;

import com.mygaienko.patterns.behavioral.strategy.generic.Trust;

/**
 * Created by dmygaenko on 19/05/2016.
 */
public class TrustTaxStrategy extends DefaultTaxStrategy<Trust> {

    public long computeTax(Trust trust) {
        return trust.isNonProfit() ? 0 : super.computeTax(trust);
    }

}
