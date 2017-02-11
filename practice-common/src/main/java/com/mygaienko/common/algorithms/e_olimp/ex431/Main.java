package com.mygaienko.common.algorithms.e_olimp.ex431;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int tests = in.nextInt();

        PrintWriter out = new PrintWriter(System.out);

        IntStream.range(0, tests).forEach(i -> {
            int n = in.nextInt();
            int k = in.nextInt();

            int[] weights = new int[n + 1];
            int[][] tree = new int[n + 1][n + 1];

            IntStream.range(1, n + 1).forEach(j -> weights[j] = in.nextInt());

            IntStream.range(1, n).forEach(j -> {
                int u = in.nextInt();
                int v = in.nextInt();
                tree[u][v] = 1;
            });

            out.println(countSets(tree, weights, n, k));
            out.flush();
        });
    }

    private static long countSets(int[][] tree, int[] weights, int n, int k) {
        long sets = 0;

        //check all vertices as possible root for set
        for (int i = 1; i <= n; i++) {

            int sum = weights[i];
            if (sum == k) {
                sets += 1;
            }

            for (int v = 1; v <= n ; v++) {
                int nextV = tree[i][v];

                if (v == 1) {

                }
            }



        }

        return sets;
    }

}
