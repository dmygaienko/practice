package com.mygaienko.common.generics;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by dmygaenko on 21/09/2016.
 */
public class CollectionsTest {

    @Test
    public void test() throws Exception {
        Collections.max(new ArrayList<TestClass>());
        Collections.max(new ArrayList<TestClass>());
        new TestClass<>().compareTo(new TestClass<>());

    }



    class TestClass<T > implements Comparable<T> {

        @Override
        public int compareTo(T o) {
            return 0;
        }
    }

    class ChildTestClass extends TestClass<ChildTestClass> implements Comparable<ChildTestClass> {

        @Override
        public int compareTo(ChildTestClass o) {
            return 0;
        }
    }

    interface MyComparable<T extends MyId> {

        public int compareTo(T o);
    }

    interface MyId {
         int getId();
    }

    class MyTestClass<T extends MyId> implements MyComparable<T>, MyId {

        @Override
        public int compareTo(T o) {
            return getId();
        }

        @Override
        public int getId() {
            return 0;
        }
    }

    class MyChildTestClass extends MyTestClass<MyChildTestClass>{

        @Override
        public int compareTo(MyChildTestClass o) {
            return getId();
        }
    }

    class MyChildChildTestClass extends MyTestClass<MyChildChildTestClass>{

        @Override
        public int compareTo(MyChildChildTestClass o) {
            return getId();
        }
    }

}