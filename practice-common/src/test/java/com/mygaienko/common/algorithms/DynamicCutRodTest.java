package com.mygaienko.common.algorithms;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by dmygaenko on 23/12/2016.
 */
public class DynamicCutRodTest {

    private final long[] prices = new long[] {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

    @Test
    public void testExecute2() {
        measureTime(() ->  assertEquals(5, DynamicCutRod.execute(prices, 2)));
    }

    @Test
    public void testExecute10() {
        measureTime(() -> assertEquals(30, DynamicCutRod.execute(prices, 10)));
    }

    @Test
    public void testExecute15() {
        measureTime(() -> assertEquals(43, DynamicCutRod.execute(prices, 15)));
    }

    @Test
    public void testExecute20() {
        measureTime(() -> assertEquals(60, DynamicCutRod.execute(prices, 20)));
    }


    @Test
    public void testExecute30() {
        measureTime(() -> assertEquals(90, DynamicCutRod.execute(prices, 30)));
    }

    @Test
    public void testExecute40() {
        measureTime(() -> assertEquals(95, DynamicCutRod.execute(prices, 32)));
    }

    @Test
    public void testDynamicExecute32() {
        measureTime(() -> assertEquals(95, DynamicCutRod.dynamicExecute(new HashMap<>(), prices, 32)));
    }

    @Test
    public void testDynamicExecute2() {
        measureTime(() -> assertEquals(5, DynamicCutRod.dynamicExecute(new HashMap<>(), prices, 2)));
    }

    @Test
    public void testDynamicExecute5() {
        measureTime(() -> assertEquals(13, DynamicCutRod.dynamicExecute(new HashMap<>(), prices, 5)));
    }

    @Test
    public void testDynamicExecute10() {
        measureTime(() -> assertEquals(30, DynamicCutRod.dynamicExecute(new HashMap<>(), prices, 10)));
    }

    @Test
    public void testDynamicExecute11() {
        measureTime(() -> assertEquals(31, DynamicCutRod.dynamicExecute(new HashMap<>(), prices, 11)));
    }

    @Test
    public void testDynamicExecute12() {
        measureTime(() -> assertEquals(35, DynamicCutRod.dynamicExecute(new HashMap<>(), prices, 12)));
    }

    @Test
    public void testDynamicExecute15() {
        measureTime(() -> assertEquals(43, DynamicCutRod.dynamicExecute(new HashMap<>(), prices, 15)));
    }

    @Test
    public void testDynamicExecute20() {
        measureTime(() -> assertEquals(60, DynamicCutRod.dynamicExecute(new HashMap<>(), prices, 20)));
    }


    @Test
    public void testMemoizedExecute10() {
        measureTime(() -> assertEquals(30, DynamicCutRod.memoizedCutRod(prices, 10)));
    }

    @Test
    public void testMemoizedExecute11() {
        measureTime(() -> assertEquals(31, DynamicCutRod.memoizedCutRod(prices, 11)));
    }

    @Test
    public void testMemoizedExecute20() {
        measureTime(() -> assertEquals(60, DynamicCutRod.memoizedCutRod(prices, 20)));
    }

    @Test
    public void testMemoizedExecute40() {
        measureTime(() -> assertEquals(120, DynamicCutRod.memoizedCutRod(prices, 40)));
    }

    @Test
    public void testMemoizedExecute80() {
        measureTime(() -> assertEquals(240, DynamicCutRod.memoizedCutRod(prices, 80)));
    }

    private void measureTime(Runnable r) {
        long start = System.nanoTime();
        r.run();
        long end = System.nanoTime();
        System.out.println("millis to execute: " + (end - start));
    }

}