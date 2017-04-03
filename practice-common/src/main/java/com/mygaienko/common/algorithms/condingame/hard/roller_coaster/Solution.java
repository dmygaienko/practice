package com.mygaienko.common.algorithms.condingame.hard.roller_coaster;

import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

//ArrayDeque
// delete by scope with index
class Solution {

    private static Map<Integer, RidedGroups> interimResults = new HashMap<>();

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int capacity = in.nextInt();
        int times = in.nextInt();

        Queue<Group> queue = initQueue(in);

        long sum = 0;
        for (int i = 0; i < times; i++) {
            RidedGroups ridedGroups = fillAttraction(sum, i, times, capacity, queue);
            if (ridedGroups.cycled) {
                i += ridedGroups.cycledIterations;
                sum += ridedGroups.cycledSum;
                ridedGroups.cycled = false;
            } else {
                sum += ridedGroups.seated;
            }
        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(sum);
    }

    private static RidedGroups fillAttraction(long sum, int i, int times, int capacity, Queue<Group> queue) {
        Group peeked = queue.peek();
        RidedGroups ridedGroups = interimResults.get(peeked.id);

        if (ridedGroups == null) {
            ridedGroups = rideGroups(capacity, queue, peeked);

            ridedGroups.prevI = i;
            ridedGroups.prevSum = sum;
            interimResults.put(peeked.id, ridedGroups);
        } else {
            predictCycles(sum, i, times, ridedGroups);

            if (!ridedGroups.cycled) {
                for (int i1 = 0; i1 < ridedGroups.groupsAtRide.size(); i1++) {
                    queue.remove();
                }
            }
        }

        if (!ridedGroups.cycled) {
            queue.addAll(ridedGroups.groupsAtRide);
        }

        return ridedGroups;
    }

    private static void predictCycles(long sum, int i, int times, RidedGroups ridedGroups) {
        if (!ridedGroups.wasPrediction) {
            int iterationsPerCycle = i - ridedGroups.prevI;
            int iterationsRemains = times - i;
            int fullCycles = iterationsRemains / iterationsPerCycle;
            if (fullCycles > 0) {
                ridedGroups.cycledIterations = fullCycles * iterationsPerCycle - 1;
                ridedGroups.cycledSum = fullCycles * (sum - ridedGroups.prevSum);
                ridedGroups.cycled = true;
            }
            ridedGroups.wasPrediction = true;
        }
    }

    private static RidedGroups rideGroups(int capacity, Queue<Group> queue, Group peeked) {
        RidedGroups ridedGroups = new RidedGroups();
        while (peeked != null && ridedGroups.seated + peeked.qty <= capacity) {
            ridedGroups.seated += queue.poll().qty;
            ridedGroups.groupsAtRide.add(peeked);
            peeked = queue.peek();
        }
        return ridedGroups;
    }

    private static Queue<Group> initQueue(Scanner in) {
        Queue<Group> queue = new ArrayDeque<>();
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            queue.add(new Group(i, in.nextInt()));
        }
        return queue;
    }

    private static class RidedGroups {
        public List<Group> groupsAtRide = new ArrayList<>();
        public long seated = 0;
        public int prevI;

        public long prevSum;
        public boolean cycled = false;
        public boolean wasPrediction;
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