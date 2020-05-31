package com.mygaienko.common.algorithms;

import com.mygaienko.common.util.TestUtils;
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
        TestUtils.measureTime(() ->  assertEquals(5, DynamicCutRod.execute(prices, 2)));
    }

    @Test
    public void testExecute10() {
        TestUtils.measureTime(() -> assertEquals(30, DynamicCutRod.execute(prices, 10)));
    }

    @Test
    public void testExecute15() {
        TestUtils.measureTime(() -> assertEquals(43, DynamicCutRod.execute(prices, 15)));
    }

    @Test
    public void testExecute20() {
        TestUtils.measureTime(() -> assertEquals(60, DynamicCutRod.execute(prices, 20)));
    }


    @Test
    public void testExecute30() {
        TestUtils.measureTime(() -> assertEquals(90, DynamicCutRod.execute(prices, 30)));
    }

    @Test
    public void testExecute40() {
        TestUtils.measureTime(() -> assertEquals(95, DynamicCutRod.execute(prices, 32)));
    }

    @Test
    public void testDynamicExecute32() {
        TestUtils.measureTime(() -> assertEquals(95, DynamicCutRod.dynamicExecute(new HashMap<>(), prices, 32)));
    }

    @Test
    public void testDynamicExecute2() {
        TestUtils.measureTime(() -> assertEquals(5, DynamicCutRod.dynamicExecute(new HashMap<>(), prices, 2)));
    }

    @Test
    public void testDynamicExecute5() {
        TestUtils.measureTime(() -> assertEquals(13, DynamicCutRod.dynamicExecute(new HashMap<>(), prices, 5)));
    }

    @Test
    public void testDynamicExecute10() {
        TestUtils.measureTime(() -> assertEquals(30, DynamicCutRod.dynamicExecute(new HashMap<>(), prices, 10)));
    }

    @Test
    public void testDynamicExecute11() {
        TestUtils.measureTime(() -> assertEquals(31, DynamicCutRod.dynamicExecute(new HashMap<>(), prices, 11)));
    }

    @Test
    public void testDynamicExecute12() {
        TestUtils.measureTime(() -> assertEquals(35, DynamicCutRod.dynamicExecute(new HashMap<>(), prices, 12)));
    }

    @Test
    public void testDynamicExecute15() {
        TestUtils.measureTime(() -> assertEquals(43, DynamicCutRod.dynamicExecute(new HashMap<>(), prices, 15)));
    }

    @Test
    public void testDynamicExecute20() {
        TestUtils.measureTime(() -> assertEquals(60, DynamicCutRod.dynamicExecute(new HashMap<>(), prices, 20)));
    }

    @Test
    public void testDynamicExecute40() {
        TestUtils.measureTime(() -> assertEquals(120, DynamicCutRod.dynamicExecute(new HashMap<>(), prices, 40)));
    }

    @Test
    public void testDynamicExecute80() {
        TestUtils.measureTime(() -> assertEquals(240, DynamicCutRod.dynamicExecute(new HashMap<>(), prices, 80)));
    }

    @Test
    public void testMemoizedExecute10() {
        TestUtils.measureTime(() -> assertEquals(30, DynamicCutRod.memoizedCutRod(prices, 10)));
    }

    @Test
    public void testMemoizedExecute11() {
        TestUtils.measureTime(() -> assertEquals(31, DynamicCutRod.memoizedCutRod(prices, 11)));
    }

    @Test
    public void testMemoizedExecute20() {
        TestUtils.measureTime(() -> assertEquals(60, DynamicCutRod.memoizedCutRod(prices, 20)));
    }

    @Test
    public void testMemoizedExecute40() {
        TestUtils.measureTime(() -> assertEquals(120, DynamicCutRod.memoizedCutRod(prices, 40)));
    }

    @Test
    public void testMemoizedExecute80() {
        TestUtils.measureTime(() -> assertEquals(240, DynamicCutRod.memoizedCutRod(prices, 80)));
    }

    @Test
    public void execute80and80() throws Exception {
        TestUtils.measureTime(() -> assertEquals(4800, ForkJoinDynamicCutRod.execute(prices, 1600)));
        TestUtils.measureTime(() -> assertEquals(4800, DynamicCutRod.dynamicExecute(new HashedMap(),prices, 1600)));
    }

}