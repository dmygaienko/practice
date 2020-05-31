package com.mygaienko.common.concurrency;

import org.junit.Test;

import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by dmygaenko on 23/06/2016.
 */
public class ConcurrentSkipListMapTest {

    private ConcurrentSkipListMap<String, String> map = new ConcurrentSkipListMap<>();

    private ExecutorService executor = Executors.newFixedThreadPool(5);

    @Test
    public void test() throws InterruptedException {
        addValue("key1", "value1");
        addValue("key7", "value7");
        addValue("key6", "value3");
        addValue("key3", "value3");
        addValue("key4", "value4");
        addValue("key2", "value2");
        addValue("key5", "value5");
        executor.awaitTermination(5, TimeUnit.SECONDS);

        assertEquals("value1", map.ceilingEntry("key1").getValue());
        System.out.println(map);
    }

    private void addValue(String key, String value) {
        executor.execute(() -> map.putIfAbsent(key, value));
    }
}
