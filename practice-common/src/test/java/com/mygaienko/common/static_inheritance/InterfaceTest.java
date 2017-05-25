package com.mygaienko.common.static_inheritance;

import org.junit.Test;

/**
 * Created by enda1n on 25.05.2017.
 */
public class InterfaceTest {

    @Test
    public void test() throws Exception {
        new BInterfaceImpl().method();
    }
}

class BInterfaceImpl implements BInterface {

}

interface AInterface {

    default void method() {
        System.out.println("AInterface.method");
    }

}

interface BInterface extends AInterface {

    @Override
    default void method() {
        AInterface.super.method();
        System.out.println("BInterface.method");
    }

}

