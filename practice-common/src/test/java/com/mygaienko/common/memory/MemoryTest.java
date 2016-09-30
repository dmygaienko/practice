package com.mygaienko.common.memory;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.util.Arrays;

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

    @Test
    public void testJol() throws Exception {
        System.out.println(ClassLayout.parseClass(Integer.class).toPrintable());
    }

    @Test
    public void testXWithJolParseInstance() throws Exception {
        System.out.println(ClassLayout.parseInstance(new TestStringClass(1, "str")).toPrintable());
    }

    @Test
    public void testX1WithJolParseClass() throws Exception {
        System.out.println(ClassLayout.parseClass(X1.class).toPrintable());
    }

    @Test
    public void testX1WithJolParseInstance() throws Exception {
        System.out.println(ClassLayout.parseInstance(new X1(1, (byte) 1, 1, 1)).toPrintable());
    }

    @Test
    public void testListWithJolParseInstance() throws Exception {
        System.out.println(ClassLayout.parseInstance(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).toPrintable());
    }

    @Test
    public void testListWithGraphLayoutParseInstance() throws Exception {
        System.out.println(GraphLayout.parseInstance(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).toPrintable());
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

    class X {                      // 8 bytes for reference to the class definition
        int a;                      // 4 bytes
        byte b;                     // 1 byte
        Integer c = new Integer(1);  // 4 bytes for a reference

        public X(int a, byte b, Integer c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
    class X1 extends X  {             // 8 bytes for reference to the class definition
        int a1;

        public X1(int a, byte b, Integer c, int a1) {
            super(a, b, c);
            this.a1 = a1;
        }
// 4 bytes
    }
}
