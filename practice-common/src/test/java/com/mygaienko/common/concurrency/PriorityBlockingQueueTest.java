package com.mygaienko.common.concurrency;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by dmygaenko on 23/06/2016.
 */
public class PriorityBlockingQueueTest {

    private final PriorityBlockingQueue<String> queue = new PriorityBlockingQueue<>(2);

    @Before
    public void setUp() throws Exception {
        queue.add("value1");
        queue.add("value2");
        queue.add("value3");
    }

    @Test
    public void testSize() throws Exception {
        assertEquals(3, queue.size());
    }

    @Test
    public void testPoll() throws Exception {
        assertEquals("value1", queue.poll());
        assertEquals("value2", queue.poll());
    }

    @Test
    public void testPol() throws Exception {
        assertEquals("value1", queue.take());
        assertEquals("value2", queue.poll());
    }

    @Test
    public void testPeek() throws Exception {
        assertEquals("value1", queue.peek());
        assertEquals("value1", queue.peek());
    }

    @Test
    public void testPollWithDelay() throws Exception {
        queue.clear();

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.add("value1");
                super.run();
            }
        }.start();

        assertEquals("value1", queue.poll(10, TimeUnit.SECONDS));


    }
}
