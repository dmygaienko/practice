package com.mygaienko.common.algorithms.condingame.medium;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.math.BigDecimal.ZERO;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        double maxSpeed = kmPerHourToMeterPerSec(in.nextInt());
        int lightCount = in.nextInt();

        List<Light> lights = readLights(in, lightCount);
        BigDecimal averageSpeed = countSpeed(maxSpeed, lights);

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(meterPerSecToKmPerHour(averageSpeed));
    }

    public static int meterPerSecToKmPerHour(BigDecimal averageSpeed) {
        return averageSpeed.multiply(new BigDecimal(3600)).divide(new BigDecimal(1000, MathContext.DECIMAL32)).intValue();
    }

    public static double kmPerHourToMeterPerSec(int i) {
        return i*1000/3600d;
    }

    public static BigDecimal countSpeed(double maxSpeed, List<Light> lights) {
        List<List<SpeedInterval>> speedIntervals = countSpeedIntervalsForAllLights(maxSpeed, lights);
        return findCommonMaxSpeed(speedIntervals);
    }

    public static BigDecimal findCommonMaxSpeed(List<List<SpeedInterval>> allSpeedIntervals) {
        SpeedInterval common = null;
        for (SpeedInterval speedInterval : allSpeedIntervals.get(0)) {
            if (common == null || common.isEmpty()) {
                common = speedInterval;
            }


            for (int i = 1; i < allSpeedIntervals.size(); i++) {
                List<SpeedInterval> allSpeedInterval = allSpeedIntervals.get(i);

                SpeedInterval merged = null;
                for (SpeedInterval interval : allSpeedInterval) {
                    merged = common.merge(interval);

                    if (!merged.isEmpty()) {
                        common = merged;
                        break;
                    }
                }

                if (merged == null || merged.isEmpty()) {
                    common = null;
                    break;
                }

            }
        }
        return common != null ? common.max : ZERO;
    }

    private static List<List<SpeedInterval>> countSpeedIntervalsForAllLights(double maxSpeed, List<Light> lights) {
       return lights.stream()
               .map(light -> countSpeedIntervalsForLight(maxSpeed, light))
               .collect(Collectors.toList());
    }

    public static List<SpeedInterval> countSpeedIntervalsForLight(double maxSpeed, Light light) {
        double minDuration = light.getDistance() / maxSpeed;

        List<SpeedInterval> speedIntervals = new ArrayList<>();
        if (minDuration <= light.getDuration()) {
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

            if (nextMinSpeed.intValue() <= maxSpeed) {
                 if (nextMaxSpeed.intValue() > maxSpeed) {
                    nextMaxSpeed = new BigDecimal(maxSpeed);
                 }
                 speedIntervals.add(new SpeedInterval(nextMinSpeed, nextMaxSpeed));
            }

        } while (nextMinSpeed.compareTo(new BigDecimal(5)) > 0 && nextMaxSpeed.compareTo(new BigDecimal(5)) > 0);

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

        public SpeedInterval(int min, int max) {
            this.min = new BigDecimal(min);
            this.max = new BigDecimal(max);
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
                if (max.compareTo(that.max) > -1) {
                    newMin = that.min;
                    newMax = that.max;
                } else if (max.compareTo(that.min) > 0){
                    newMin = that.min;
                    newMax = max;
                }

            } else {
                if (that.max.compareTo(max) > -1) {
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
