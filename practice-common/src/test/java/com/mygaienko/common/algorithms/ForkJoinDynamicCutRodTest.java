package com.mygaienko.common.algorithms;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by enda1n on 24.12.2016.
 */
public class ForkJoinDynamicCutRodTest {

    private final long[] prices = new long[] {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

    @Test
    public void execute2() throws Exception {
        assertEquals(5, ForkJoinDynamicCutRod.execute(prices, 2));
    }

    @Test
    public void execute10() throws Exception {
        assertEquals(30, ForkJoinDynamicCutRod.execute(prices, 10));
    }

    @Test
    public void execute30() throws Exception {
        assertEquals(90, ForkJoinDynamicCutRod.execute(prices, 30));
    }

    @Test
    public void execute40() throws Exception {
        assertEquals(120, ForkJoinDynamicCutRod.execute(prices, 40));
    }


}