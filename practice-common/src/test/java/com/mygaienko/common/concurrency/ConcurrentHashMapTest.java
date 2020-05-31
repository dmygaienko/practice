package com.mygaienko.common.concurrency;

import org.junit.Test;

import java.util.concurrent.*;

public class ConcurrentHashMapTest {

    private ExecutorService executorService = Executors.newFixedThreadPool(6);

    @Test
    public void test() throws InterruptedException {
        ConcurrentMap<Long, Long> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 200; i++) {
            long finalI = i;
            if (i % 20 == 0) {
                executorService.execute(() -> System.out.println(map));
            }
            executorService.execute(() -> map.compute(finalI, this::add));
        }

        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println(map);
    }

    private Long add(Long prev, Long next) {
        Long result = 0L;
        result = prev != null ? result + prev : result;
        result = next != null ? result + next : result;
        return result;
    }
}
