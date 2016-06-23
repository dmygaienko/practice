package com.mygaienko.common.concurrency;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.Assert.assertFalse;

/**
 * Created by dmygaenko on 23/06/2016.
 */
public class LinkedBlockingQueueTest {


    @Test(expected = IllegalStateException.class)
    public void test1() throws Exception {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(2);
        queue.add("value1");
        queue.add("value2");
        queue.add("value3");
    }

    @Test
    public void test2() throws Exception {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(2);
        queue.add("value1");
        queue.add("value2");
        assertFalse(queue.offer("value3"));
    }
}
