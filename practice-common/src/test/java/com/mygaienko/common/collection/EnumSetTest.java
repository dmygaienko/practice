package com.mygaienko.common.collection;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class EnumSetTest {

    private EnumSet<TestEnum> testEnumSet = EnumSet.allOf(TestEnum.class);
    private Set<TestEnum> testHashSet = new HashSet<>(Arrays.asList(TestEnum.values()));

    @Test
    public void test() {
        testSet(testEnumSet);
        testSet(testEnumSet);
        testSet(testEnumSet);
        testSet(testHashSet);
        testSet(testHashSet);
        testSet(testHashSet);
    }

    private void testSet(Set<TestEnum> testSet) {
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        for (int i = 0; i < 100000; i++) {
            boolean contains = testSet.contains(TestEnum.values()[1000 % TestEnum.values().length]);
            System.out.print(contains);
        }

        stopWatch.stop();

        System.out.println("Time: " + stopWatch.getTime());
    }

    private enum TestEnum {
        E1, E21, E31, E41, E51, E61, E71, E81, E91,
        E2, E22, E32, E42, E52, E62, E72, E82, E92,
        E3, E23, E33, E43, E53, E63, E73, E83, E93,
        E4, E24, E34, E44, E54, E64, E74, E84, E94,
        E5, E25, E35, E45, E55, E65, E75, E85, E95,
        E6, E26, E36, E46, E56, E66, E76, E86, E96,
        E7, E27, E37, E47, E57, E67, E77, E87, E97,
        E8, E28, E38, E48, E58, E68, E78, E88, E98,
        E9, E29, E39, E49, E59, E69, E79, E89, E99,
        E10, E120, E130, E140, E150, E160, E170, E180, E190,
        E11, E121, E131, E141, E151, E161, E171, E181, E191,
        E12, E122, E132, E142, E152, E162, E172, E182, E192,
        E13, E123, E133, E143, E153, E163, E173, E183, E193,
        E14, E124, E134, E144, E154, E164, E174, E184, E194,
    }
}
