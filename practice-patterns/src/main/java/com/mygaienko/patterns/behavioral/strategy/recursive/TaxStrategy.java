package com.mygaienko.patterns.behavioral.strategy.recursive;

/**
 * Created by dmygaenko on 19/05/2016.
 */
interface TaxStrategy<P extends TaxPayer<P>> {
    public long computeTax(P p);
}
