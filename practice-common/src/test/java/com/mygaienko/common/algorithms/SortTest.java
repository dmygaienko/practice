package com.mygaienko.common.algorithms;

import com.mygaienko.common.util.TestUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

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
        fillCollection(sortedList);
        sortedSet = new HashSet<>(sortedList);
    }

    private void fillCollection(List<Integer> sortedList) {
        for (int i = 0; i < 1000; i++) {
            sortedList.add(i);
        }
    }

    @Test
    public void simpleSort() throws Exception {
        TestUtils.measureTime(() -> sort(generateData(1000000, 1000)));
    }

    @Test
    public void testJoinWithSortedList() throws Exception {
        TestUtils.measureTime(() -> joinWithSortedList(generateData(1000000, 1000)));
    }

    @Test
    public void testJoinWithSortedSet() throws Exception {
        TestUtils.measureTime(() -> joinWithSortedSet(generateData(1000000, 1000)));
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

    private List<Integer> generateData(int size, int bound) {
        ArrayList<Integer> data = new ArrayList<>(size);
        for (int i = 0; i < size; i ++) {
            data.add(random.nextInt(bound));
        }
        return data;
    }


}
