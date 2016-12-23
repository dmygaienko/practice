package com.mygaienko.common.algorithms;

import java.util.Map;

/**
 * Created by dmygaenko on 23/12/2016.
 */
public class DynamicCutRod {

    public static long execute(long[] prices, long length) {
        if (length == 0) return 0;

        long sum = 0;

        for (int i = 1; i <= length && i < prices.length; i++) {
            sum = Math.max(sum, prices[i] + execute(prices, length - i));
        }

        return sum;
    }

    public static long dynamicExecute(Map<Long, Long> results, long[] prices, long length) {
        if (length == 0) return 0;

        long sum = 0;

        for (int i = 1; i <= length; i++) {
            Long sumForCurrentLength;

            if (i < prices.length) {
                sumForCurrentLength = prices[i] + dynamicExecute(results, prices, length - i);
            } else {
                sumForCurrentLength = results.get(i);
                if (sumForCurrentLength == null) {
                    sumForCurrentLength = 0L;
                }
            }

            sum = Math.max(sum, sumForCurrentLength);
            results.put(length, sum);
        }

        return sum;
    }
}
