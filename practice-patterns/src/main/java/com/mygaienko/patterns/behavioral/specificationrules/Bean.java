package com.mygaienko.patterns.behavioral.specificationrules;

/**
 * Created by dmygaenko on 23/03/2016.
 */
public class Bean {

    private String aAttribute;
    private String bAttribute;

    public String getaAttribute() {
        return aAttribute;
    }

    public Bean(String aAttribute, String bAttribute) {
        this.aAttribute = aAttribute;
        this.bAttribute = bAttribute;
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
}
