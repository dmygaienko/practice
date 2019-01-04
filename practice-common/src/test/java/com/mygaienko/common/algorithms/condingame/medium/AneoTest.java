package com.mygaienko.common.algorithms.condingame.medium;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class AneoTest {

    @Test
    public void test() {
        List<Solution.SpeedInterval> speedIntervals = Solution.countSpeedIntervalsForLight(25, new Solution.Light(200, 10));
        System.out.println(speedIntervals);
    }

    @Test
    public void testCountSpeed() {
        System.out.println(Solution.countSpeed(25, Arrays.asList(
                new Solution.Light(300, 30),
                new Solution.Light(1500, 30),
                new Solution.Light(3000, 30)
        )));
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

}