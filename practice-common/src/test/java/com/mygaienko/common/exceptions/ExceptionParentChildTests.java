package com.mygaienko.common.exceptions;

import org.junit.Test;

/**
 * Created by dmygaenko on 10/10/2016.
 */
public class ExceptionParentChildTests {

    @Test
    public void testNotGeneric() {
        A a = new B();
        try {
            a.execute();
        } catch (AException e) {
      //} catch (BException e) { cannot catch only BException
            e.printStackTrace();
        }

        B b = new B();
        try {
            b.execute();
        } catch (BException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void tesGeneric() {
        AGeneric<BException> a = new BGeneric();
        try {
            a.execute();
        } catch (BException e) {
            e.printStackTrace();
        }

        AGeneric<AException> a1 = new AGeneric<>();
        try {
            a1.execute();
        } catch (AException e) {
            e.printStackTrace();
        }
    }

    static class A {
        public void execute() throws AException {
        }
    }

    static class B extends A {
        @Override
        public void execute() throws BException {
        }
    }

    static class AException extends Exception {
    }

    static class BException extends AException {
    }

    interface Generic<E extends Exception> {
        void execute() throws E;
    }


    static class AGeneric<E extends Exception> implements Generic<E> {

        public void execute() throws E {
        }
    }

    static class BGeneric extends AGeneric<BException> {

        @Override
        public void execute() throws BException {
        }
    }

}
