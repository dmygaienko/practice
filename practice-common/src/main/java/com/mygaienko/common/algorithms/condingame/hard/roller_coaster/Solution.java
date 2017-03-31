package com.mygaienko.common.algorithms.condingame.hard.roller_coaster;

import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

//ArrayDeque
// delete by scope with index
class Solution {

    private static Map<Integer, RidedGroup> interimResults = new HashMap<>();
    private static boolean wasPrediction;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int capacity = in.nextInt();
        int times = in.nextInt();

        Queue<Group> queue = initQueue(in);

        long sum = 0;
        for (int i = 0; i < times; i++) {
            RidedGroup ridedGroup = fillAttraction(sum, i, times, capacity, queue);
            if (ridedGroup.cycled) {
                i += ridedGroup.cycledIterations;
                sum += ridedGroup.cycledSum;
                ridedGroup.cycled = false;
            } else {
                sum += ridedGroup.seated;
            }
        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(sum);
    }

    private static RidedGroup fillAttraction(long sum, int i, int times, int capacity, Queue<Group> queue) {
        Group peeked = queue.peek();
        RidedGroup ridedGroup = staffAttraction(sum, i, times, capacity, queue, peeked);
        if (!ridedGroup.cycled) {
            queue.addAll(ridedGroup.groupsAtRide);
        }
        return ridedGroup;
    }

    private static RidedGroup staffAttraction(long sum, int i, int times, int capacity, Queue<Group> queue, Group peeked) {
        RidedGroup ridedGroup = interimResults.get(peeked.id);

        if (ridedGroup == null) {
            ridedGroup = new RidedGroup();
            interimResults.put(peeked.id, ridedGroup);

            ridedGroup.prevI = i;
            ridedGroup.prevSum = sum;

            while (peeked != null && ridedGroup.seated + peeked.qty <= capacity) {
                ridedGroup.seated += queue.poll().qty;
                ridedGroup.groupsAtRide.add(peeked);
                peeked = queue.peek();
            }

        } else {
            if (!wasPrediction) {
                //TODO
                int iterationsPerCycle = i - ridedGroup.prevI;
                int iterationsRemains = times - i;
                int fullCycles = iterationsRemains / iterationsPerCycle;
                if (fullCycles > 0) {
                    ridedGroup.cycledIterations = fullCycles * iterationsPerCycle - 1;
                    ridedGroup.cycledSum = fullCycles * (sum - ridedGroup.prevSum);
                    ridedGroup.cycled = true;
                }
                wasPrediction = true;
            } else {
                for (int i1 = 0; i1 < ridedGroup.groupsAtRide.size(); i1++) {
                    queue.remove();
                }
            }
        }

        return ridedGroup;
    }

    private static Queue<Group> initQueue(Scanner in) {
        Queue<Group> queue = new ArrayDeque<>();
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            queue.add(new Group(i, in.nextInt()));
        }
        return queue;
    }

    private static class RidedGroup {
        public List<Group> groupsAtRide = new ArrayList<>();
        public long seated = 0;
        public int prevI;
        public long prevSum;
        public boolean cycled = false;
        public int cycledIterations;
        public long cycledSum;
    }

    private static class Group {
        private final int id;
        private final int qty;

        public Group(int id, int qty) {
            this.id = id;
            this.qty = qty;
        }
    }
}