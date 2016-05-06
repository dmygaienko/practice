package com.mygaienko.patterns.creational.singleton;

/**
 * Created by dmygaenko on 06/05/2016.
 */
public class Context {

    private String valueA;

    public Context(String valueA) {
        this.valueA = valueA;
    }

    public String getValueA() {
        return valueA;
    }

    public void setValueA(String valueA) {
        this.valueA = valueA;
    }

}
