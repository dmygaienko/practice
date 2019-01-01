package com.mygaienko.common.algorithms.condingame.medium;

import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int maxSpeed = in.nextInt();
        int lightCount = in.nextInt();

        List<Light> lights = readLights(in, lightCount);
        int averageSpeed = countSpeed(maxSpeed, lights);

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(averageSpeed);
    }

    private static int countSpeed(int maxSpeed, List<Light> lights) {
        Map<Light, List<SpeedInterval>> speedIntervals = countSpeedIntervals(maxSpeed, lights);
        return findCommonMaxSpeed(speedIntervals);
    }

    private static int findCommonMaxSpeed(Map<Light, List<SpeedInterval>> speedIntervals) {
        return 0;
    }

    private static Map<Light, List<SpeedInterval>> countSpeedIntervals(int maxSpeed, List<Light> lights) {
        return null;
    }

    private static List<Light> readLights(Scanner in, int lightCount) {
        List<Light> lights = new ArrayList<>();
        for (int i = 0; i < lightCount; i++) {
            int distance = in.nextInt();
            int duration = in.nextInt();
            lights.add(new Light(distance, duration));
        }
        return lights;
    }

    private static class Light {

        private final int distance;
        private final int duration;

        private Light(int distance, int duration) {
            this.distance = distance;
            this.duration = duration;
        }

        public int getDistance() {
            return distance;
        }

        public int getDuration() {
            return duration;
        }
    }

    private static class SpeedInterval {
        private final int min;
        private final int max;

        private SpeedInterval(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public int getMin() {
            return min;
        }

        public int getMax() {
            return max;
        }
    }

}
