package com.mygaienko.common.functional;

import java.util.function.Function;

/**
 * Created by enda1n on 16.05.2017.
 */
public class Metric {

    private static final String TOTAL = "TOTAL";
    
    private String nettingLevel;
    private String tradeNumber;
    private String counterParty;
    private String book;

    public String getNettingLevel() {
        return nettingLevel;
    }

    public void setNettingLevel(String nettingLevel) {
        this.nettingLevel = nettingLevel;
    }

    public String getTradeNumber() {
        return tradeNumber;
    }

    public void setTradeNumber(String tradeNumber) {
        this.tradeNumber = tradeNumber;
    }

    public String getCounterParty() {
        return counterParty;
    }

    public void setCounterParty(String counterParty) {
        this.counterParty = counterParty;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public void fold(Metric m2) {
        Function<Function<Metric, String>, String> resolver = getter -> resolve(this, m2, getter);

        nettingLevel = resolver.apply(Metric::getNettingLevel);
        tradeNumber = resolver.apply(Metric::getTradeNumber);
        counterParty = resolver.apply(Metric::getCounterParty);
        book = resolver.apply(Metric::getBook);
    }
    
    public String resolve(Metric m1, Metric m2, Function<Metric, String> getter) {
        return getter.apply(m1).equals(getter.apply(m2)) ? getter.apply(m1) : TOTAL;
    }

}
