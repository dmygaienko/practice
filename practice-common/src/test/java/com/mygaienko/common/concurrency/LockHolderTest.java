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
            executor.submit(getWriteTask());
        }
        executor.shutdown();
        executor.awaitTermination(60, TimeUnit.SECONDS);
    }

    private Runnable getWriteTask() throws InterruptedException {
        return () -> {
            while (!LockHolder.INSTANCE.writeLock.tryAcquire()) {
                System.out.println("tryAcquire");
            }
            System.out.println("acquired");
            LockHolder.INSTANCE.writeLock.release();
            System.out.println("release");
        };
    }
}