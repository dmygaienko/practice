package com.mygaienko.patterns.behavioral.specificationrules;

/**
 * Created by dmygaenko on 23/03/2016.
 */
public class Context {

    private String aAttribute;
    private String bAttribute;

    public String getaAttribute() {
        return aAttribute;
    }

    public void setaAttribute(String aAttribute) {
        this.aAttribute = aAttribute;
    }

    public String getbAttribute() {
        return bAttribute;
    }

    public void setbAttribute(String bAttribute) {
        this.bAttribute = bAttribute;
    }

    @Override
    public String toString() {
        return "Context{" +
                "aAttribute='" + aAttribute + '\'' +
                ", bAttribute='" + bAttribute + '\'' +
                '}';
    }
}
