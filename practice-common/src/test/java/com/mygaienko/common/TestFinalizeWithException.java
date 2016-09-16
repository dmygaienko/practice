package com.mygaienko.common;

import org.junit.Test;

/**
 * Created by dmygaenko on 16/09/2016.
 */
public class TestFinalizeWithException {

    @Test
    public void test() {
        new TestClass().testMethod();
    }

    class TestClass {

        public void testMethod() {
            System.out.println("test method");
        }


        @Override
        protected void finalize() throws Throwable {
            System.out.println("finalize method before Exception");
            throw new RuntimeException("Exception throwd from finalize method");
            //System.out.println("finalize method after Exception");
        }
    }
}
