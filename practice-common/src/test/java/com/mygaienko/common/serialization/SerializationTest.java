package com.mygaienko.common.serialization;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by dmygaenko on 22/06/2016.
 */
public class SerializationTest {

    @Test
    public void testTestClass() throws Exception {
        testClass(new TestClass("test", 1));
    }

    @Test
    public void testTestReadWriteClass() throws Exception {
        testClass(new TestReadWriteClass("test", 1));
    }

    private void testClass(Object testClass) throws IOException,
            ClassNotFoundException {
        Object actual = serializeDeserialize(testClass);

        assertEquals(testClass, actual);
    }

    private Object serializeDeserialize(Object testClass) throws
            IOException, ClassNotFoundException {
        byte[] bytes = serialize(testClass);
        return deserialize(bytes);
    }

    private Object deserialize(byte[] bytes) throws IOException,
            ClassNotFoundException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new
                ObjectInputStream(inputStream);

        return objectInputStream.readObject();
    }

    private byte[] serialize(Object testClass) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new
                ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new
                ObjectOutputStream(byteArrayOutputStream);

        objectOutputStream.writeObject(testClass);
        return byteArrayOutputStream.toByteArray();
    }

    @Test
    public void testNotSerializable() throws Exception {
        testClass(new TestReadWriteClass("test", 1));
    }

    @Test
    public void testParentNotSerializable() throws Exception {
        B testClass = new B("a1", "b1");
        assertNotEquals(testClass, serializeDeserialize(testClass));
    }

    @Test
    public void testParentSerializable() throws Exception {
        B1 testClass = new B1("a1", "b1");
        assertEquals(testClass, serializeDeserialize(testClass));
    }

    @Test
    public void testParentSerializableC2() throws Exception {
        C2 testClass = new C2("a1", "b1", "c1");
        assertEquals(testClass, serializeDeserialize(testClass));
    }


    static class A {
        String a1;

        public A() {
        }

        public A(String a1) {
            this.a1 = a1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            A a = (A) o;

            return a1 != null ? a1.equals(a.a1) : a.a1 == null;

        }

        @Override
        public int hashCode() {
            return a1 != null ? a1.hashCode() : 0;
        }
    }

    static class B extends A implements Serializable {
        String b1;

        public B() {
        }

        public B(String a1, String b1) {
            super(a1);
            this.b1 = b1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            B b = (B) o;

            return b1 != null ? b1.equals(b.b1) : b.b1 == null;

        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + (b1 != null ? b1.hashCode() : 0);
            return result;
        }
    }

    static class A1 implements Serializable {
        String a1;

        public A1() {
        }

        public A1(String a1) {
            this.a1 = a1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            A1 a = (A1) o;

            return a1 != null ? a1.equals(a.a1) : a.a1 == null;

        }

        @Override
        public int hashCode() {
            return a1 != null ? a1.hashCode() : 0;
        }
    }

    static class B1 extends A1 {
        String b1;

        public B1() {
        }

        public B1(String a1, String b1) {
            super(a1);
            this.b1 = b1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            B1 b = (B1) o;

            return b1 != null ? b1.equals(b.b1) : b.b1 == null;

        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + (b1 != null ? b1.hashCode() : 0);
            return result;
        }
    }

    static class A2 implements Serializable {
        String a1;

        public A2() {
        }

        public A2(String a1) {
            this.a1 = a1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            A2 a = (A2) o;

            return a1 != null ? a1.equals(a.a1) : a.a1 == null;

        }

        @Override
        public int hashCode() {
            return a1 != null ? a1.hashCode() : 0;
        }
    }

    static class B2 extends A2 {
        String b1;

        public B2() {
            System.out.println("Default Constructor B2 is called");
        }

        public B2(String a1, String b1) {
            super(a1);
            this.b1 = b1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            B2 b = (B2) o;

            return b1 != null ? b1.equals(b.b1) : b.b1 == null;

        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + (b1 != null ? b1.hashCode() : 0);
            return result;
        }
    }

    static class C2 extends B2 {
        String c1;

        public C2() {
        }

        public C2(String a1, String b1, String c1) {
            super(a1, b1);
            this.c1 = c1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            C2 b = (C2) o;

            return c1 != null ? c1.equals(b.c1) : b.c1 == null;

        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + (c1 != null ? c1.hashCode() : 0);
            return result;
        }
    }



}