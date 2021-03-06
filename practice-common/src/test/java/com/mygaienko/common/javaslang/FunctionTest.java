package com.mygaienko.common.javaslang;

import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function3;
import io.vavr.Function4;
import io.vavr.control.Option;
import org.junit.Test;

import static io.vavr.Function2.lift;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by enda1n on 15.05.2017.
 */
public class FunctionTest {

    @Test
    public void curryingTest() throws Exception {
        Function4<Integer, Integer, Integer, Integer, Integer> sum = getSum();

        Function1<Integer, Function1<Integer, Function1<Integer, Integer>>> apply1 = sum.curried().apply(1);

        Integer apply = apply1.apply(2).apply(3).apply(4);

        assertThat(apply, is(10));

    }

    private Function4<Integer, Integer, Integer, Integer, Integer> getSum() {
        return (a, b, c, d) -> a + b + c + d;
    }

    private Function2<Integer, Integer, Integer> multiply() {
        return (a, b) -> a * b;
    }

    @Test
    public void partApplicationTest() throws Exception {
        Function4<Integer, Integer, Integer, Integer, Integer> sum = (a, b, c, d) -> a + b + c + d;

        Function3<Integer, Integer, Integer, Integer> apply1 = sum.apply(1);

        Integer apply = apply1.apply(2, 3).apply(4);

        assertThat(apply, is(10));

    }

    @Test
    public void compositionTest() throws Exception {
        Integer apply = getSum().andThen(multiply().apply(2)).apply(1, 2, 3, 4);

        assertThat(apply, is(20));
    }

    @Test
    public void liftTestWithNone() throws Exception {
        Function2<Integer, Integer, Option<Integer>> lift = lift(FunctionTest::sum);
        Option<Integer> apply = lift.apply(-1, -1);

        assertThat(apply, equalTo(Option.none()));
    }

    @Test
    public void liftTestWithResult() throws Exception {
        Function2<Integer, Integer, Option<Integer>> lift = lift(FunctionTest::sum);
        Option<Integer> apply = lift.apply(1, 2);

        assertThat(apply.get(), equalTo(3));
    }

    static int sum(int first, int second) {
        if (first < 0 || second < 0) {
            throw new IllegalArgumentException("Only positive integers are allowed");
        }
        return first + second;
    }
}
