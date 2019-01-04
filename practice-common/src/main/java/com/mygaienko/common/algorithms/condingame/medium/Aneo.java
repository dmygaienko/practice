package com.mygaienko.common.algorithms.condingame.medium;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
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

    public static int countSpeed(int maxSpeed, List<Light> lights) {
        Map<Light, List<SpeedInterval>> speedIntervals = countSpeedIntervalsForAllLights(maxSpeed, lights);
        return findCommonMaxSpeed(speedIntervals);
    }

    private static int findCommonMaxSpeed(Map<Light, List<SpeedInterval>> speedIntervals) {
        Map<Integer, Integer> speedIndex = new TreeMap<>();
        for (List<SpeedInterval> intervals : speedIntervals.values()) {
            for (SpeedInterval interval : intervals) {
                for (int i = interval.getMin().intValue(); interval.getMax().compareTo(new BigDecimal(i)) > -1; i++) {
                    speedIndex.compute(i, (k, v) -> v == null ? 1 : v + 1);
                }
            }
        }

        return speedIndex.entrySet().stream()
                .filter(entry -> entry.getValue().equals(speedIntervals.size()))
                .findFirst()
                .get()
                .getKey();
    }

    private static Map<Light, List<SpeedInterval>> countSpeedIntervalsForAllLights(int maxSpeed, List<Light> lights) {
       return lights.stream()
               .collect(toMap(identity(), light -> countSpeedIntervalsForLight(maxSpeed, light)));
    }

    public static List<SpeedInterval> countSpeedIntervalsForLight(int maxSpeed, Light light) {
        int minDuration = light.getDistance() / maxSpeed;

        List<SpeedInterval> speedIntervals = new ArrayList<>();
        if (minDuration < light.getDuration()) {
            BigDecimal minSpeed = new BigDecimal(light.getDistance()).divide(new BigDecimal(light.getDuration()), MathContext.DECIMAL32);
            speedIntervals.add(new SpeedInterval(minSpeed, new BigDecimal(maxSpeed)));
        }

        int nextDuration = 0;
        BigDecimal nextMinSpeed;
        BigDecimal nextMaxSpeed;
        do {
            nextDuration = nextDuration + light.getDuration() * 2;
            nextMinSpeed = new BigDecimal(light.getDistance()).divide(new BigDecimal(nextDuration), MathContext.DECIMAL32);
            nextMaxSpeed = new BigDecimal(light.getDistance()).divide(new BigDecimal(nextDuration - light.getDuration()), MathContext.DECIMAL32);

            if (nextMinSpeed.intValue() < maxSpeed && nextMaxSpeed.intValue() < maxSpeed) {
                    speedIntervals.add(new SpeedInterval(nextMinSpeed, nextMaxSpeed));
            }

        } while (nextMinSpeed.compareTo(ONE) > 0 && nextMaxSpeed.compareTo(ONE) > 0);

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

    public static class Light {

        private final int distance;
        private final int duration;

        public Light(int distance, int duration) {
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

    public static class SpeedInterval {
        private final BigDecimal min;
        private final BigDecimal max;

        public SpeedInterval(BigDecimal min, BigDecimal max) {
            this.min = min;
            this.max = max;
        }

        public BigDecimal getMin() {
            return min;
        }

        public BigDecimal getMax() {
            return max;
        }

        public SpeedInterval merge(SpeedInterval that) {
            BigDecimal newMin = BigDecimal.ZERO;
            BigDecimal newMax = BigDecimal.ZERO;

            if (min.compareTo(that.min) < 0) {
                if (max.compareTo(that.max) > 0) {
                    newMin = that.min;
                    newMax = that.max;
                } else if (max.compareTo(that.min) > 0){
                    newMin = that.min;
                    newMax = max;
                }

            } else if (that.min.compareTo(min) < 0) {
                if (that.max.compareTo(max) > 0) {
                    newMin = min;
                    newMax = max;
                } else if (that.max.compareTo(min) > 0) {
                    newMin = min;
                    newMax = that.max;
                }
            }

            return new SpeedInterval(newMin, newMax);
        }

        public boolean isEmpty() {
            return min.equals(ZERO) && max.equals(ZERO);
        }

        @Override
        public String toString() {
            return "SpeedInterval{" +
                    "min=" + min +
                    ", max=" + max +
                    '}';
        }

    }

}
