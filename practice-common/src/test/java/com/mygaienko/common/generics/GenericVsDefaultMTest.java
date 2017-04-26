package com.mygaienko.common.generics;

/**
 * Created by dmygaenko on 26/04/2017.
 */
public class GenericVsDefaultMTest {

    static class CTest implements ITest<String> {

        @Override
        public void m(String s) {

        }
    }

    interface ITest<T> {
        default void m(String s) {

        }

        default void m(T s) {
        }
    }
}
