package com.mygaienko.common.concurrency;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created by enda1n on 26.05.2016.
 */
public class CyclicBarrierTest {

    private CyclicBarrier barrier = new CyclicBarrier(3);

    @Test
    public void test() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 9; i ++) {
            executor.submit(getAwaitTask(i));
        }
        executor.shutdown();
        executor.awaitTermination(60, TimeUnit.SECONDS);
        System.out.println("main out");

    }

    private Runnable getAwaitTask(int i) {
        return () -> {
            try {
                System.out.println("before await: " + i);
                barrier.await();
                System.out.println("after await: " + i);
                System.out.println("getNumberWaiting(): " + barrier.getNumberWaiting());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        };
    }
}
