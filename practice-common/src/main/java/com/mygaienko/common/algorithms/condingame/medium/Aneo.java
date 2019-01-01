package com.mygaienko.common.algorithms.condingame.medium;

import java.util.*;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int maxSpeed = kmPerHourToMeterPerSec(in.nextInt());
        int lightCount = in.nextInt();

        List<Light> lights = readLights(in, lightCount);
        int averageSpeed = countSpeed(maxSpeed, lights);

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(averageSpeed);
    }

    private static int kmPerHourToMeterPerSec(int i) {
        return i*1000/3600;
    }

    private static int countSpeed(int maxSpeed, List<Light> lights) {
        Map<Light, List<SpeedInterval>> speedIntervals = countSpeedIntervalsForAllLights(maxSpeed, lights);
        return findCommonMaxSpeed(speedIntervals);
    }

    private static int findCommonMaxSpeed(Map<Light, List<SpeedInterval>> speedIntervals) {
        Collection<List<SpeedInterval>> values = speedIntervals.values();
        for (int i = 0; i < values.size(); i++) {

        }
        return 0;
    }

    private static Map<Light, List<SpeedInterval>> countSpeedIntervalsForAllLights(int maxSpeed, List<Light> lights) {
       return lights.stream()
               .collect(toMap(identity(), light -> countSpeedIntervalsForLight(maxSpeed, light)));
    }

    private static List<SpeedInterval> countSpeedIntervalsForLight(int maxSpeed, Light light) {
        int minDuration = light.getDistance() / maxSpeed;

        List<SpeedInterval> speedIntervals = new ArrayList<>();
        if (minDuration < light.getDuration()) {
            int minSpeed = light.getDistance()/light.getDuration();
            speedIntervals.add(new SpeedInterval(minSpeed, maxSpeed));
        }

        int nextDuration = 0;
        int nextMinSpeed;
        int nextMaxSpeed;
        do {
            nextDuration = nextDuration + light.getDuration() * 2;
            nextMaxSpeed = light.getDistance()/nextDuration;
            nextMinSpeed = light.getDistance()/(nextDuration - light.getDuration());

            speedIntervals.add(new SpeedInterval(nextMinSpeed, nextMaxSpeed));
        } while (nextMinSpeed > 0 && nextMaxSpeed > 0);

        return speedIntervals;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Light light = (Light) o;

            if (distance != light.distance) return false;
            return duration == light.duration;
        }

        @Override
        public int hashCode() {
            int result = distance;
            result = 31 * result + duration;
            return result;
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
