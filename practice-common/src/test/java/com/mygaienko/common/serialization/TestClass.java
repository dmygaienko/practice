package com.mygaienko.common.serialization;

import java.io.Serializable;

/**
 * Created by dmygaenko on 22/06/2016.
 */
public class TestClass implements Serializable {

    private String s;
    private int i;

    public TestClass(String s, int i) {
        this.s = s;
        this.i = i;
    }

    public TestClass() {
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestClass testClass = (TestClass) o;

        if (i != testClass.i) return false;
        return s != null ? s.equals(testClass.s) : testClass.s == null;

    }

    @Override
    public int hashCode() {
        int result = s != null ? s.hashCode() : 0;
        result = 31 * result + i;
        return result;
    }
}
