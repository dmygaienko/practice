package com.mygaienko.common.concurrency;

import org.junit.Test;

import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;

/**
 * Created by dmygaenko on 23/06/2016.
 */
public class ConcurrentSkipListMapTest {

    private ConcurrentSkipListMap<String, String> map = new ConcurrentSkipListMap<>();

    private Executor executor = Executors.newFixedThreadPool(5);

    @Test
    public void test() {
        addValue("key1", "value1");
        addValue("key2", "value2");
        addValue("key3", "value3");
        addValue("key4", "value4");
        addValue("key5", "value5");

        assertEquals("value1", map.ceilingEntry("key1").getValue());
    }

    private void addValue(String key, String value) {
        executor.execute(() -> map.putIfAbsent(key, value));
    }
}
