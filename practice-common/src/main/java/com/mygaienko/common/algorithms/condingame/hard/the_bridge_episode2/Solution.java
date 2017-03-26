package com.mygaienko.common.algorithms.condingame.hard.the_bridge_episode2;

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    private static Point[][] bridge = new Point[4][];

    private static Map<Integer, List<List<Action>>> bikeActions = new HashMap<>();

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int M = in.nextInt(); // the amount of motorbikes to control
        int V = in.nextInt(); // the minimum amount of motorbikes that must survive

        initBridge(in);

        // game loop
        while (true) {
            int speed = in.nextInt(); // the motorbikes' speed
            for (int bikeId = 0; bikeId < M; bikeId++) {
                int x = in.nextInt(); // x coordinate of the motorbike
                int y = in.nextInt(); // y coordinate of the motorbike
                int a = in.nextInt(); // indicates whether the motorbike is activated "1" or destroyed "0"

                if (a != 0) {
                    checkWay(bikeId, speed, x, y);
                }
            }

            findCommonActions();

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            // A single line containing one of 6 keywords: SPEED, SLOW, JUMP, WAIT, UP, DOWN.
            System.out.println("SPEED");
        }
    }

    private static void checkWay(int bikeId, int speed, int x, int y) {
        checkHoleInFront();
        checkIncreaseSpeed();
        checkDecreaseSpeed();
    }

    private static void initBridge(Scanner in) {
        String safeWay = "..........";
        initLine(0, in.next() + safeWay);
        initLine(1, in.next() + safeWay);
        initLine(2, in.next() + safeWay);
        initLine(3, in.next() + safeWay);
    }

    private static void initLine(int y, String s) {
        String[] way = s.split("");
        for (int x = 0, wayLength = way.length; x < wayLength; x++) {
            String step = way[x];
            boolean safe = ".".equals(step);
            bridge[y][x] = new Point(safe);
        }
    }

    private static class Point {
        private final boolean safe;

        public Point(boolean safe) {
            this.safe = safe;
        }
    }

    private static class Action {

    }
}