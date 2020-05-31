package com.mygaienko.common.concurrency;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by enda1n on 26.05.2016.
 */
public class CountDownLatchHolderTest {

    @Test
    public void testCountDownLatch() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 5 ; i++) {
            executor.submit(getUnlockTask(i));
        }

        for (int i = 0; i < 5; i++) {
            executor.submit(getAwaitedTask(i));
        }

        executor.shutdown();
        executor.awaitTermination(1000, TimeUnit.SECONDS);
        System.out.println("main");
    }

    private Runnable getAwaitedTask(int i) {
        return () -> {
            try {
                System.out.println("await: " + i);
                CountDownLatchHolder.INSTANCE.latch.await();
                System.out.println("run: " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }

    private Runnable getUnlockTask(int i) {
        return () -> {
            CountDownLatchHolder.INSTANCE.latch.countDown();
            System.out.println("count down: " + i);
            System.out.println("count: " +  CountDownLatchHolder.INSTANCE.latch.getCount());
        };
    }
}