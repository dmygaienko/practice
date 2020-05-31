package com.mygaienko.common.algorithms;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static com.mygaienko.common.util.TestUtils.fillCollection;
import static com.mygaienko.common.util.TestUtils.generateData;
import static com.mygaienko.common.util.TestUtils.measureTime;

/**
 * Created by dmygaenko on 29/12/2016.
 */
public class SortTest {

    private Random random;
    private List<Integer> sortedList;
    private Set<Integer> sortedSet;

    @Before
    public void setUp() throws Exception {
        random = new Random();
        sortedList = new ArrayList<>();
        fillCollection(sortedList, 1000);
        sortedSet = new HashSet<>(sortedList);
    }

    @Test
    public void simpleSort() throws Exception {
        measureTime(() -> sort(generateData(1000000, 1000)));
    }

    @Test
    public void testJoinWithSortedList() throws Exception {
        measureTime(() -> joinWithSortedList(generateData(1000000, 1000)));
    }

    @Test
    public void testJoinWithSortedSet() throws Exception {
        measureTime(() -> joinWithSortedSet(generateData(1000000, 1000)));
    }

    private List<Integer> sort(List<Integer> integers) {
        Collections.sort(integers);
        System.out.println("Size: " + integers.size());
        return integers;
    }

    private List<Integer> joinWithSortedList(List<Integer> integers) {
        sortedList.retainAll(integers);
        System.out.println("Integers size: " + integers.size());
        System.out.println("Sorted size: " + sortedList.size());
        return integers;
    }

    private List<Integer> joinWithSortedSet(List<Integer> integers) {
        sortedSet.retainAll(integers);
        System.out.println("Integers size: " + integers.size());
        System.out.println("Sorted size: " + sortedSet.size());
        return integers;
    }

}
