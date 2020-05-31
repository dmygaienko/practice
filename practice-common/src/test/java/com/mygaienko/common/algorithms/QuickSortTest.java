package com.mygaienko.common.algorithms;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by enda1n on 31.12.2016.
 */
public class QuickSortTest {

    @Test
    public void test() throws Exception {
        int[] array = {9, 8, 7, 6, 5, 4, 3, 2, 1, 13};
        QuickSort.sort(array);
        Arrays.toString(array);

    }
}