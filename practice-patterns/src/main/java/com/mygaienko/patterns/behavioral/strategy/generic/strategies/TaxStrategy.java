package com.mygaienko.patterns.behavioral.strategy.generic.strategies;


import com.mygaienko.patterns.behavioral.strategy.generic.TaxPayer;

/**
 * Created by dmygaenko on 19/05/2016.
 */
public interface TaxStrategy<P extends TaxPayer<P>> {
    public long computeTax(P p);
}
