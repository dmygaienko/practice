package com.mygaienko.common.concurrency;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by enda1n on 26.05.2016.
 */
public class ScheduledThreadPoolExecutorTest {

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);

    @Test
    public void test() throws Exception {
        for (int i = 0; i < 5; i++) {
            executor.scheduleWithFixedDelay(getTask(i), 2, 5, TimeUnit.SECONDS);
        }

        Thread.sleep(10000);

        /*executor.shutdown();
        executor.awaitTermination(60, TimeUnit.SECONDS);
        System.out.println("main out");*/
    }

    private Runnable getTask(int i) {
        return () -> System.out.println("runnable: " + i);
    }
}
