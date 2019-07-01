package com.mygaienko.common.concurrency;

import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class CasTest {

    @Test
    public void testAtomicInteger() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();

        AtomicInteger atomicInteger = new AtomicInteger();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        stopWatch.start();
        for (int i = 0; i < 100000; i++) {
            int finalI = i;
            executorService.execute(() -> atomicInteger.addAndGet(finalI));
        }

        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        stopWatch.stop();

        System.out.println(atomicInteger.get() + " in " + stopWatch.getTotalTimeSeconds());
    }

    @Test
    public void testLongAdder() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();

        LongAdder longAdder = new LongAdder();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        stopWatch.start();
        for (int i = 0; i < 100000; i++) {
            int finalI = i;
            executorService.execute(() -> longAdder.add(finalI));
        }

        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        stopWatch.stop();

        System.out.println(longAdder.longValue() + " in " + stopWatch.getTotalTimeSeconds());
    }

}
