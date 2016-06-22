package com.mygaienko.common.serialization;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by dmygaenko on 22/06/2016.
 */
public class TestReadWriteClass implements Serializable {

    private String s;
    private int i;

    public TestReadWriteClass(String s, int i) {
        this.s = s;
        this.i = i;
    }

    public TestReadWriteClass() {
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

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        System.out.println("writeObject");
        //out.defaultWriteObject();
        out.writeObject(s);
        out.writeInt(i);
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        System.out.println("readObject");
        //in.defaultReadObject();
         s = (String) in.readObject();
         i = in.readInt();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestReadWriteClass testClass = (TestReadWriteClass) o;

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
