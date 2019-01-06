package com.mygaienko.common.algorithms.condingame.medium;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class AneoTest {

    @Test
    public void test() {
        List<Solution.SpeedInterval> speedIntervals = Solution.countSpeedIntervalsForLight(new BigDecimal(25), new Solution.Light(200, 10));
        System.out.println(speedIntervals);
    }

    @Test
    public void testCountSpeed() {
        BigDecimal x = Solution.countSpeed(Solution.kmPerHourToMeterPerSec(90), Arrays.asList(
                new Solution.Light(300, 30),
                new Solution.Light(1500, 30),
                new Solution.Light(3000, 30)
        ));

        System.out.println(Solution.meterPerSecToKmPerHour(x));
    }

    @Test
    public void testCountSpeed1() {
        BigDecimal x = Solution.countSpeed(Solution.kmPerHourToMeterPerSec(50), Arrays.asList(
                new Solution.Light(200, 15)
        ));

        System.out.println(Solution.meterPerSecToKmPerHour(x));
    }

    @Test
    public void testCountSpeed2() {
        BigDecimal x = Solution.countSpeed(Solution.kmPerHourToMeterPerSec(50), Arrays.asList(
                new Solution.Light(200, 10)
        ));

        System.out.println(Solution.meterPerSecToKmPerHour(x));
    }

    @Test
    public void testCountSpeed4() {
        BigDecimal x = Solution.countSpeed(Solution.kmPerHourToMeterPerSec(90), Arrays.asList(
                new Solution.Light(300, 30),
                new Solution.Light(1500, 20),
                new Solution.Light(3000, 10)
        ));

        System.out.println(Solution.meterPerSecToKmPerHour(x));
    }

    @Test
    public void testMerge14_23() {
        System.out.println(new Solution.SpeedInterval(new BigDecimal(1), new BigDecimal(4))
                .merge(new Solution.SpeedInterval(new BigDecimal(2), new BigDecimal(3))));

        System.out.println(new Solution.SpeedInterval(new BigDecimal(2), new BigDecimal(3))
                .merge(new Solution.SpeedInterval(new BigDecimal(1), new BigDecimal(4))));
    }

    @Test
    public void testMerge13_24() {
        System.out.println(new Solution.SpeedInterval(new BigDecimal(1), new BigDecimal(3))
                .merge(new Solution.SpeedInterval(new BigDecimal(2), new BigDecimal(4))));

        System.out.println(new Solution.SpeedInterval(new BigDecimal(2), new BigDecimal(4))
                .merge(new Solution.SpeedInterval(new BigDecimal(1), new BigDecimal(3))));
    }

    @Test
    public void testMerge12_34() {
        System.out.println(new Solution.SpeedInterval(new BigDecimal(1), new BigDecimal(2))
                .merge(new Solution.SpeedInterval(new BigDecimal(3), new BigDecimal(4))));

        System.out.println(new Solution.SpeedInterval(new BigDecimal(3), new BigDecimal(4))
                .merge(new Solution.SpeedInterval(new BigDecimal(1), new BigDecimal(2))));
    }

    @Test
    public void testMerge12_13() {
        System.out.println(new Solution.SpeedInterval(new BigDecimal(1), new BigDecimal(2))
                .merge(new Solution.SpeedInterval(new BigDecimal(1), new BigDecimal(3))));

        System.out.println(new Solution.SpeedInterval(new BigDecimal(1), new BigDecimal(3))
                .merge(new Solution.SpeedInterval(new BigDecimal(1), new BigDecimal(2))));
    }

    @Test
    public void testMerge10_25() {
        System.out.println(new Solution.SpeedInterval(10, 25)
                .merge(new Solution.SpeedInterval(25, 25)));
    }

    @Test
    public void testFindCommonMaxSpeed() {
        BigDecimal commonMaxSpeed = Solution.findCommonMaxSpeed(Arrays.asList(
                Arrays.asList(new Solution.SpeedInterval(1, 3)),
                Arrays.asList(new Solution.SpeedInterval(1, 2)),
                Arrays.asList(new Solution.SpeedInterval(1, 4))
        ));

        System.out.println(commonMaxSpeed);
    }
}