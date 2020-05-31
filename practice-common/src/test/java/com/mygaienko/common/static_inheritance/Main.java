package com.mygaienko.common.static_inheritance;

import org.junit.Test;

/**
 * Created by enda1n on 31.01.2017.
 */
public class Main {

    @Test
    public void test() {
        new B();
    }
}

class A {

    static String a1 = "a1";

    static {
        System.out.println(a1);
    }

}

class B extends A {

    static String b1 = "b1";

    static {
        System.out.println(b1);
    }
}


