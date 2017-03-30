package com.mygaienko.common.algorithms.condingame.hard.roller_coaster;

import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int capacity = in.nextInt();
        int C = in.nextInt();

        int sum = 0;

        Queue<Integer> queue = initQueue(in);

        for (int i = 0; i < C; i++) {
            sum += fillAttraction(capacity, queue);
        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(sum);
    }

    private static int fillAttraction(int capacity, Queue<Integer> queue) {
        List<Integer> groupsAtRide = new ArrayList<>();
        int seated = 0;

        Integer peek = queue.peek();
        while (peek != null && seated + peek <= capacity) {
            seated += queue.poll();
            groupsAtRide.add(peek);
            peek = queue.peek();
        }

        queue.addAll(groupsAtRide);
        return seated;
    }

    private static Queue<Integer> initQueue(Scanner in) {
        Queue<Integer> queue = new LinkedList<>();
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            queue.add(in.nextInt());
        }
        return queue;
    }
}