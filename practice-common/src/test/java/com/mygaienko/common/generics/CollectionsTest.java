package com.mygaienko.common.generics;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by dmygaenko on 21/09/2016.
 */
public class CollectionsTest {

    @Test
    public void test() throws Exception {
        Collections.max(new ArrayList<TestClass>());

    }



    class TestClass implements Comparable<TestClass> {

        @Override
        public int compareTo(TestClass o) {
            return 0;
        }
    }

    class ChildTestClass  extends TestClass implements Comparable<ChildTestClass> {

        @Override
        public int compareTo(ChildTestClass o) {
            return 0;
        }
    }
}