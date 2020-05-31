package com.mygaienko.common.concurrency;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LockHolderTest {

    @Test
    public void testName() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i ++) {
            executor.submit(getWriteTask(i));
        }
        executor.shutdown();
        executor.awaitTermination(60, TimeUnit.SECONDS);
        System.out.println("main out");
    }

    private Runnable getWriteTask(int i) throws InterruptedException {
        return () -> {
            while (!LockHolder.INSTANCE.writeLock.tryAcquire()) {
                System.out.println("tryAcquire: " + i);
            }
            System.out.println("acquired: " + i);
            LockHolder.INSTANCE.writeLock.release();
            System.out.println("release: " + i);
        };
    }
}