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
        /*    if (results.get(prices.length)) {

            }*/
            sum = Math.max(sum, prices[i] + execute(prices, length - i));
        }

        return sum;
    }

    public static long dynamicExecute(Map<Long, Long> results, long[] prices, long length) {
        Long sum = results.get(length);
        if (sum != null) {
            return sum;
        }

        if (length == 0) return 0;

        for (int i = 1; i <= length && i < prices.length; i++) {
            sum =  prices[i] + dynamicExecute(results, prices, length - i);
        }

        results.put(length, sum);

        return sum;
    }

    public static long memoizedCutRod(long[] prices, int length) {
        long[] memoized = new long[length+1];

        return memoizedCutRod(memoized, prices, length);

    }

    private  static long memoizedCutRod(long[] memoized, long[] prices, int length) {
        if (memoized[length] > 0) {
            return memoized[length];
        }

        if (length ==0) return 0;

        long sum = 0;
        for (int i = 1; i <= length && i < prices.length; i++) {
            sum = Math.max(sum,  prices[i] + memoizedCutRod(memoized, prices, length - i));
        }
        memoized[length] = sum;
        return sum;
    }


}
