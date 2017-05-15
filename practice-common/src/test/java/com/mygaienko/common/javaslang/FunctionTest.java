package com.mygaienko.common.javaslang;

import io.vavr.Function1;
import io.vavr.Function3;
import io.vavr.Function4;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by enda1n on 15.05.2017.
 */
public class FunctionTest {

    @Test
    public void curryingTest() throws Exception {
        Function4<Integer, Integer, Integer, Integer, Integer> sum = (a, b, c, d) -> a + b + c + d;

        Function1<Integer, Function1<Integer, Function1<Integer, Integer>>> apply1 = sum.curried().apply(1);

        Integer apply = apply1.apply(2).apply(3).apply(4);

        assertThat(apply, is(10));

    }

    @Test
    public void partApplicationTest() throws Exception {
        Function4<Integer, Integer, Integer, Integer, Integer> sum = (a, b, c, d) -> a + b + c + d;

        Function3<Integer, Integer, Integer, Integer> apply1 = sum.apply(1);

        Integer apply = apply1.apply(2, 3).apply(4);

        assertThat(apply, is(10));

    }
}
