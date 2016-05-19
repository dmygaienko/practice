package com.mygaienko.patterns.behavioral.strategy;

/**
 * Created by dmygaenko on 19/05/2016.
 */
public interface TaxStrategy<P extends TaxPayer> {
    public long computeTax(P p);
}
