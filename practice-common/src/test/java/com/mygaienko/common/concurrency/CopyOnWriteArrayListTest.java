package com.mygaienko.common.concurrency;

import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by dmygaenko on 23/06/2016.
 */
public class CopyOnWriteArrayListTest {

    private final CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    @Test
    public void test() {
        addValue("value1");
        addValue("value2");
        iterate();
        addValue("value3");
        addValue("value4");
    }

    private void addValue(String value) {
        executor.execute(() -> copyOnWriteArrayList.add(value));
    }

    private void iterate() {
        executor.execute(() -> {
            for (String s : copyOnWriteArrayList) {
                assertNotEquals("value4", s);
            }
        });

    }
}
