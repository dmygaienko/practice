package com.mygaienko.common.concurrency;

import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;

public class CasTest {

    @Test
    public void testAtomicInteger() throws InterruptedException {
        AtomicLong atomicLong = new AtomicLong();

        StopWatch stopWatch = executeConcurrently(atomicLong::addAndGet);

        System.out.println(atomicLong.get() + " in " + stopWatch.getTotalTimeSeconds());
    }

    @Test
    public void testLongAdder() throws InterruptedException {
        LongAdder longAdder = new LongAdder();

        StopWatch stopWatch = executeConcurrently(longAdder::add);

        System.out.println(longAdder.longValue() + " in " + stopWatch.getTotalTimeSeconds());
    }

    private void barrier(CyclicBarrier barrier) {
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread() + " started at " + System.currentTimeMillis());
    }

    private StopWatch executeConcurrently(Consumer<Long> consumer) throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(20);
        ExecutorService executorService = Executors.newFixedThreadPool(20);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (long i = 0; i < 100000; i++) {
            long finalI = i;
            executorService.execute(() -> {
                barrier(barrier);
                consumer.accept(finalI);
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        stopWatch.stop();
        return stopWatch;
    }

}
