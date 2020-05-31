package com.mygaienko.patterns.creational.singleton;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

/**
 * Created by dmygaenko on 06/05/2016.
 */
public class ContextHolderTest {

    @Test
    public void test() {
        ContextHolder.INSTANCE.setContext(new Context("valueA"));
        assertEquals("valueA", ContextHolder.INSTANCE.getContext().getValueA());
    }

    @Test
    public void testRunnable() {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        pool.submit(new TestTask("1"));
        pool.submit(new TestTask("2"));
        pool.submit(new TestTask("3"));
        pool.submit(new TestTask("4"));
        pool.submit(new TestTask("5"));
    }

    private static class TestTask implements Runnable{
        private String value;

        public TestTask(String value) {
            this.value = value;
        }

        @Override
        public void run() {
            ContextHolder.INSTANCE.setContext(new Context(value));

            String valueA = ContextHolder.INSTANCE.getContext().getValueA();
            System.out.println("ValueA: " + valueA);
            assertEquals(value, valueA);
        }
    }

}