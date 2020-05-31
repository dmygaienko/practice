package com.mygaienko.common.algorithms.e_olimp.ex5;

import java.io.PrintWriter;
import java.util.*;
import java.util.stream.LongStream;

/**
 * Created by dmygaenko on 07/02/2017.
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();

        PrintWriter out = new PrintWriter(System.out);
        out.println(execute(a));
        out.flush();
    }

    public static long execute(int k) {
        if (k < 1 || k > 50) return 0;
        if (k == 1) return 1;
        if (k == 2) return 4;

        long n = 0;

        Map<Long, Set<Pair>> presentation = new HashMap<>();
        return process(k, n, presentation);
    }

    private static long process(int k, long n, Map<Long, Set<Pair>> presentation) {

       do {
            n += 12;
           computePairs(n, presentation);
       } while (presentation.get(n) == null || presentation.get(n).size() != k);

        //System.out.println(presentation.get(n));
        return n;
    }

    public static void computePairs(long n, Map<Long, Set<Pair>> presentation) {
        LongStream.range(1, new Double(Math.pow(n, 0.5)).longValue() + 2).forEach(i -> computePair(presentation, n, i));
    }

    public static void computePair(Map<Long, Set<Pair>> presentation, long n, long a) {
        if (n % a == 0) {

            long fraction = n / a;
            presentation.compute(n, (key, set) -> {
                if (set == null) {
                    set = new TreeSet<>();
                }

                long pairA;
                long pairB;

                if (fraction > a) {
                    pairA = a;
                    pairB = fraction;
                } else {
                    pairA = fraction;
                    pairB = a;
                }

                set.add(new Pair(pairA, pairB));
                return set;
            });
        }
    }

    static class Pair implements Comparable{

        private Long a;
        private Long b;

        public Pair(Long a, Long b) {
            this.a = a;
            this.b = b;
        }

        public Long getA() {
            return a;
        }

        public Long getB() {
            return b;
        }

        @Override
        public int compareTo(Object o) {
            return a.compareTo(((Pair)o).a);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (a != null ? !a.equals(pair.a) : pair.a != null) return false;
            return b != null ? b.equals(pair.b) : pair.b == null;

        }

        @Override
        public int hashCode() {
            int result = a != null ? a.hashCode() : 0;
            result = 31 * result + (b != null ? b.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "a=" + a +
                    ", b=" + b +
                    "}\n";
        }
    }
}
