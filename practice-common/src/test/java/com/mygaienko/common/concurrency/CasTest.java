package com.mygaienko.common.concurrency;

import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;

public class CasTest {

    @Test
    public void testAtomicInteger() throws InterruptedException {
        AtomicLong atomicLong = new AtomicLong();

        StopWatch stopWatch = executeConcurrently(delta -> atomicLong.addAndGet(delta));

        System.out.println(atomicLong.get() + " in " + stopWatch.getTotalTimeSeconds());
    }

    @Test
    public void testLongAdder() throws InterruptedException {
        LongAdder longAdder = new LongAdder();

        StopWatch stopWatch = executeConcurrently(x -> longAdder.add(x));

        System.out.println(longAdder.longValue() + " in " + stopWatch.getTotalTimeSeconds());
    }

    private StopWatch executeConcurrently(Consumer<Long> consumer) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        stopWatch.start();
        for (long i = 0; i < 1000000; i++) {
            long finalI = i;
            executorService.execute(() -> consumer.accept(finalI));
        }

        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        stopWatch.stop();
        return stopWatch;
    }

}
