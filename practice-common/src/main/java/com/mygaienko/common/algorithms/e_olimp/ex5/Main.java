package com.mygaienko.common.algorithms.e_olimp.ex5;

import java.io.PrintWriter;
import java.util.*;
import java.util.stream.IntStream;

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

    public static int execute(int k) {
        if (k < 1 || k > 50) return 0;
        if (k == 1) return 1;

        int n = k;

        Map<Integer, Set<Pair>> presentation = new HashMap<>();
        return process(k, n, presentation);
    }

    private static int process(int k, int n, Map<Integer, Set<Pair>> presentation) {

        while (presentation.get(n-1) == null || presentation.get(n-1).size() != k) {
            int finalN = ++n;

            IntStream.range(1, n + 1)
                    .forEach(i -> {
                        computePairs(presentation, finalN, i);
                    });
        }

        return n-1;
    }

    private static void computePairs(Map<Integer, Set<Pair>> presentation, int finalN, int i) {
        int a = i;
        int fraction = finalN / a;
        if (finalN % a == 0) {

            presentation.compute(finalN, (key, set) -> {
                if (set == null) {
                    set = new HashSet<>();
                }

                int pairA;
                int pairB;

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

    static class Pair {

        private Integer a;
        private Integer b;

        public Pair(Integer a, Integer b) {
            this.a = a;
            this.b = b;
        }

        public Integer getA() {
            return a;
        }

        public Integer getB() {
            return b;
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
    }
}
