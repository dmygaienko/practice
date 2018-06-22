package com.mygaienko.common.stream;

import org.junit.Test;

import java.util.stream.IntStream;

/**
 * Created by dmygaenko on 17/05/2017.
 */
public class IntStreamTest {

    @Test
    public void testBuilder() throws Exception {
        boolean b = IntStream.builder()
                .add(0)
                .add(1)
                .add(2)
                .add(3)
                .build()
                .anyMatch(i -> i == 3);

        System.out.println(b);
    }
}
