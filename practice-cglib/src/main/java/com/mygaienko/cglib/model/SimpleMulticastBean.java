package com.mygaienko.cglib.model;

/**
 * Created by dmygaenko on 28/01/2016.
 */
public class SimpleMulticastBean implements DelegationProvider {
    private String value;
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}

interface DelegationProvider {
    void setValue(String value);
}
