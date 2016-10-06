package com.mygaienko.common.exceptions;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by dmygaenko on 06/10/2016.
 */
public class ExceptionTests {

    static class IOE1 extends IOException {
    }

    static class IOE2 extends IOException {
    }

    static class IOE3 extends IOException {
    }

    interface Task<V, E extends IOException> {
        void execute(V v)  throws E;
    }

    static class Task1 implements Task<Object, IOE1>{

        @Override
        public void execute(Object o) throws IOE1 {
            throw new IOE1();
        }
    }

    static class Task2 implements Task<Object, IOE2>{

        @Override
        public void execute(Object o) throws IOE2 {
            throw new IOE2();
        }
    }




    static <E extends IOException> void proccess1() throws E {
        throw (E) new IOE1();
    }

    static void safeProcess() {
        try {
            proccess1();
        } catch (IOException e) {
            System.out.println("EXX");
        }
    }

    @Test
    public void test() throws Exception {
        safeProcess();
    }

    @Test
    public void testTask() {
        try {
            new Task1().execute(new Object());
        } catch (IOE1 ioe1) {
            ioe1.printStackTrace();
        }

        try {
            new Task1().execute(new Object());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            new Task2().execute(new Object());
        } catch (IOE2 ioe1) {
            ioe1.printStackTrace();
        }
    }
}
