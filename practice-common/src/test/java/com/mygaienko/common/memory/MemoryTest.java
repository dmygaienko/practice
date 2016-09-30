package com.mygaienko.common.memory;

import org.junit.Test;

/**
 * http://blog.javabenchmark.org/2013/05/java-instrumentation-tutorial.html
 */
public class MemoryTest {

    @Test
    public void test() throws Exception {
        Agent.getObjectSize(new TestClass(1));
        Agent.getObjectSize(new TestStringClass(1, "str"));
        Agent.getObjectSize("str");
    }

    private class TestClass {
        int id;

        public TestClass(int id) {
            this.id = id;
        }
    }

    private class TestStringClass {
        int id;
        String str;
        String str2;
        String str3;

        public TestStringClass(int id, String str) {
            this.id = id;
            this.str = str;
            this.str2 = str + 2;
            this.str2 = str + 3;
        }
    }
}
