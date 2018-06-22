package com.mygaienko.common.static_inheritance;

import org.junit.Test;

/**
 * Created by dmygaenko on 25/05/2017.
 */
public class ClassTest {

    @Test
    public void test() throws Exception {
        Child1 child1 = new Child1();
        child1.printNumber();

        Child2 child2 = new Child2();
        child2.printNumber();
        child1.printNumber();

        Abstract.number = 1;

        child2.printNumber();
        child1.printNumber();
    }
}


abstract class Abstract {

    public static double number;

    static {
       number = Math.random();
    }

    public void printNumber() {
        System.out.println(number);
    }

}

class Child1 extends Abstract {

}

class Child2 extends Abstract {

}
