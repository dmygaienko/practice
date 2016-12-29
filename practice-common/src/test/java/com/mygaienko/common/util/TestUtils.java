package com.mygaienko.common.util;

/**
 * Created by dmygaenko on 29/12/2016.
 */
public class TestUtils {

    public static void measureTime(Runnable r) {
        long start = System.nanoTime();
        r.run();
        long end = System.nanoTime();
        System.out.println("millis to execute: " + (end - start));
    }
}
