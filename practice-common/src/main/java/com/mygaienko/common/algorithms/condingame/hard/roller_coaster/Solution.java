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
            sum += fillAttraction(sum, i, times, capacity, queue);
        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(sum);
    }

    private static long fillAttraction(long sum, int i, int times, int capacity, Queue<Group> queue) {
        Group peeked = queue.peek();
        RidedGroup ridedGroup = staffAttraction(sum, i, times, capacity, queue, peeked);

        queue.addAll(ridedGroup.groupsAtRide);
        return ridedGroup.seated;
    }

    private static RidedGroup staffAttraction(long sum, int i, int times, int capacity, Queue<Group> queue, Group peeked) {
        RidedGroup ridedGroup = interimResults.get(peeked.id);

        if (ridedGroup == null) {
            ridedGroup = new RidedGroup();
            interimResults.put(peeked.id, ridedGroup);

            while (peeked != null && ridedGroup.seated + peeked.qty <= capacity) {
                ridedGroup.seated += queue.poll().qty;
                ridedGroup.groupsAtRide.add(peeked);
                ridedGroup.prevI = i;
                ridedGroup.prevSum = sum;
                peeked = queue.peek();
            }
        } else {
            if (!wasPrediction) {
                //TODO
                int iterationsPerCycle = i - ridedGroup.prevI;
                int iterationsRemains = times - i;
                int fullCycles = iterationsRemains / iterationsPerCycle;

                wasPrediction = true;
            } else {
                for (int i1 = 0; i < ridedGroup.groupsAtRide.size(); i1++) {
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