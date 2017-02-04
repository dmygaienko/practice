package com.mygaienko.common.util;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toCollection;

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

    public static TreeMap<String, String> getTreeMap(int i, int i1) {
        return getMap(i, i1, TreeMap::new);
    }

    public static HashMap<String, String> getHashMap(int i, int i1) {
        return getMap(i, i1, HashMap::new);
    }

    public static LinkedHashMap<String, String> getLinkedHashMap(int i, int i1) {
        return getMap(i, i1, LinkedHashMap::new);
    }


    private static <T extends Map<String, String>> T getMap(int i, int i1, Supplier<T> mapSupplier) {
        return IntStream
                .range(i, ++i1)
                .boxed()
                .collect(Collectors.toMap(String::valueOf, String::valueOf, (v1, v2) -> v1 + v2, mapSupplier));
    }

    public static ArrayList<String> getArrayList(int i, int i1) {
        return IntStream
                .range(i, ++i1)
                .boxed()
                .map(String::valueOf)
                .collect(toCollection(ArrayList::new));
    }
}
