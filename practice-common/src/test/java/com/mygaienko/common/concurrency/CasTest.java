package com.mygaienko.common.concurrency;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CasTest {

    @Test
    public void test() throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executorService.execute(() -> atomicInteger.addAndGet(finalI));
        }

        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);

        System.out.println(atomicInteger.get());
    }
}
