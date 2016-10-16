package com.mygaienko.common.functional;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.mygaienko.common.functional.MemoizerTest.Memoizer.callMemoized;

/**
 * Functional programming in java
 * <p>
 * Created by enda1n on 16.10.2016.
 */
public class MemoizerTest {

    @Test
    public void testMemoization() throws Exception {
        final List<Integer> priceValues = Arrays.asList(2, 1, 1, 2, 2, 2, 1, 8, 9, 15);

        final RodCutterBasic rodCutter = new RodCutterBasic(priceValues);

        System.out.println(rodCutter.maxProfit(22));

    }

    public static class Memoizer {

        public static <T, R> R callMemoized(final BiFunction<Function<T, R>, T, R> biFunction, final T input) {

            Function<T, R> memoized = new Function<T, R>() {
                private final Map<T, R> store = new HashMap<>();

                public R apply(final T input) {
                    return store.computeIfAbsent(input, key -> biFunction.apply(this, key));
                }

            };

            // start chain
            return memoized.apply(input);
        }
    }

    public static class RodCutterBasic {
        final List<Integer> prices;

        public RodCutterBasic(final List<Integer> pricesForLength) {
            prices = pricesForLength;
        }

        public int maxProfit(final int rodLength) {
            return callMemoized(
                    (final Function<Integer, Integer> memoized, final Integer length) -> {
                        int profit = (length <= prices.size()) ? prices.get(length - 1) : 0;
                        for (int i = 1; i < length; i++) {
                            int priceWhenCut = memoized.apply(i) + memoized.apply(length - i);
                            if (profit < priceWhenCut) profit = priceWhenCut;
                        }
                        return profit;
                    }, rodLength);
        }
    }
}
