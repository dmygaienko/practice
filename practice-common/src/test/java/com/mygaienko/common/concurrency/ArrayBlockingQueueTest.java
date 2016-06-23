package com.mygaienko.common.concurrency;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;

import static org.junit.Assert.assertFalse;

/**
 * Created by dmygaenko on 23/06/2016.
 */
public class ArrayBlockingQueueTest {

    @Test(expected = IllegalStateException.class)
    public void test() {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(2);
        queue.add("value1");
        queue.add("value2");
        queue.add("value3");
    }

    @Test
    public void test1() {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(2);
        queue.add("value1");
        queue.add("value2");
        assertFalse(queue.offer("value3"));
    }


}
