package com.mygaienko.cglib.model;

/**
 * Created by enda1n on 28.01.2016.
 */
public class SampleBean implements SampleInterface {

    private String value;

    public String test(String input) {
        return "Hello world!";
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
