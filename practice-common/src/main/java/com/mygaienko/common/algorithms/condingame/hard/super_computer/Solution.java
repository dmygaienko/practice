package com.mygaienko.common.algorithms.condingame.hard.super_computer;

import java.util.*;
import java.io.*;
import java.math.*;
import java.util.concurrent.atomic.LongAdder;

import static java.util.Collections.singletonList;
import static java.util.Comparator.naturalOrder;

/**
 *
 * In the Computer2000 data center, you are responsible for planning the usage of a supercomputer for scientists. ​
 * Therefore you've decided to organize things a bit by planning everybody’s tasks.
 * The logic is simple: the higher the number of calculations which can be performed, the more people you can satisfy.
 *
 *  	Rules
 * Scientists give you the starting day of their calculation and the number of consecutive days they need to reserve the calculator.
 *
 * For example:
 * Calculation	Starting Day	Duration
 * A	2	5
 * B	9	7
 * C	15	6
 * D	9	3
 * Calculation A starts on day 2 and ends on day 6
 *
 * Calculation B starts on day 9 and ends on day 15
 *
 * Calculation starts on day 15 and ends on day 20
 *
 * Calculation D starts on day 9 and ends on day 11
 *
 * In this example, it’s not possible to carry out all the calculations because the periods for B and C overlap.
 * 3 calculations maximum can be carried out: A, D and C.
 *
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
public class Solution {

    public static void main(String args[]) {
        System.err.println("running");
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();

        TreeMap<Integer, Integer> requests = new TreeMap<>();
        for (int i = 0; i < N; i++) {
            int startDay = in.nextInt();
            int duration = in.nextInt();
            requests.compute(startDay, (k, v) -> (v == null) ? duration : Math.min(v, duration));
        }
        System.err.println(requests);

        LongAdder quantity = new LongAdder();
        satisfyRequests(quantity, requests);


        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(quantity);
    }

    private static void satisfyRequests(LongAdder quantity, TreeMap<Integer, Integer> requests) {
        Map.Entry<Integer, Integer> startDayEntry = requests.firstEntry();
        satisfyRequest(quantity, startDayEntry.getKey(), startDayEntry, requests);
    }

    private static void satisfyRequest(LongAdder quantity, Integer nextOpenDay, Map.Entry<Integer, Integer> requestDayEntry,
                                       TreeMap<Integer, Integer> requests) {
        Integer day = requestDayEntry.getKey();

        boolean isOpen = false;
        if (day >= nextOpenDay) {
            quantity.increment();
            isOpen = true;
        }

        Map.Entry<Integer, Integer> nextRequestDayEntry = requests.higherEntry(day);

        if (nextRequestDayEntry != null) {
            if (isOpen) {
                nextOpenDay = day + requestDayEntry.getValue();
            }

            satisfyRequest(quantity, nextOpenDay, nextRequestDayEntry, requests);
        }
    }

}
