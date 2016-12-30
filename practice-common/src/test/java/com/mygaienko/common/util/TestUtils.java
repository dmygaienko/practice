package com.mygaienko.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by dmygaenko on 29/12/2016.
 */
public class TestUtils {

    private static final Random random = new Random();

    public static void measureTime(Runnable r) {
        long start = System.nanoTime();
        r.run();
        long end = System.nanoTime();
        System.out.println("millis to execute: " + (end - start));
    }

    public static void fillCollection(List<Integer> sortedList, int size) {
        for (int i = 0; i < size; i++) {
            sortedList.add(i);
        }
    }

    public static List<Integer> generateData(int size, int bound) {
        ArrayList<Integer> data = new ArrayList<>(size);
        for (int i = 0; i < size; i ++) {
            data.add(random.nextInt(bound));
        }
        return data;
    }
}
