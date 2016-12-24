package com.mygaienko.common.algorithms;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by dmygaenko on 23/12/2016.
 */
public class ForkJoinDynamicCutRod {

    public static long execute(long[] prices, long length) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(2);
        return forkJoinPool.invoke(new CutRodAction(new ConcurrentHashMap<>(), prices, length));
    }

    static class CutRodAction extends RecursiveTask<Long> {

        private Map<Long, Long> results;
        private long[] prices;
        private long length;

        public CutRodAction(Map<Long, Long> results, long[] prices, long length) {
            this.results = results;
            this.prices = prices;
            this.length = length;
        }

        @Override
        protected Long compute() {
            Long sum = results.get(length);
            if (sum != null) {
                return sum;
            }

            if (length == 0) return 0L;

            sum = 0L;
            for (int i = 1; i <= length && i < prices.length; i++) {
                try {
                    sum = Math.max(sum, prices[i] + new CutRodAction(results, prices, length - i).fork().get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            results.put(length, sum);

            return sum;
        }
    }




}
