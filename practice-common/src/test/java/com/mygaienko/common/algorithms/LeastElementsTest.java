package com.mygaienko.common.algorithms;

import org.junit.Test;

import java.util.*;

import static com.mygaienko.common.util.TestUtils.generateData;

/**
 * Created by dmygaenko on 29/12/2016.
 */
public class LeastElementsTest {

    @Test
    public void name() throws Exception {
        List<Integer> integers = leastN(generateData(1000, 100000), 10);
        System.out.println(integers);
    }

    @Test
    public void testPriorityQueue() throws Exception {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        pq.add(1);
        pq.add(5);
        pq.add(4);
        pq.add(3);
        pq.add(2);
        System.out.println(pq);
        for (int i = 0; i < 5; i++) {
            System.out.println(pq.poll());
        }
    }

    public static <T extends Comparable<T>> List<T> leastN(Collection<T> inputs, int n) {
        assert n > 0;
        PriorityQueue<T> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (T t : inputs) {
            if (pq.size() < n) {
                pq.add(t);
            } else if (pq.peek().compareTo(t) > 0) {
                pq.poll();
                pq.add(t);
            }
        }
        List<T> list = new ArrayList<>(pq);
        Collections.sort(list);
        return list;
    }
}
