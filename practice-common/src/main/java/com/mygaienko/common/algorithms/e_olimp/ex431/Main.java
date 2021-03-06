package com.mygaienko.common.algorithms.e_olimp.ex431;

import com.mygaienko.common.algorithms.PathCombinations;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

            List<List<Integer>> tree = new ArrayList<>(n + 1);
            List<List<List<Integer>>> paths = new ArrayList<>(n + 1);

            IntStream.range(1, n + 1).forEach(j -> {
                weights[j] = in.nextInt();
                tree.add(j, new ArrayList<>());
                paths.add(j, new ArrayList<>());
            });

            IntStream.range(1, n).forEach(j -> {
                int u = in.nextInt();
                int v = in.nextInt();
                
                tree.get(u).add(v);

            });

            out.println(countTree(tree, weights, n, k, paths));
            out.flush();
        });
    }

    private static long countTree(List<List<Integer>> tree, int[] weights, int n, int k, List<List<List<Integer>>> paths) {
        countPathsInSubtree(tree, 0, paths);
        return 0l;
    }

    private static void countPathsInSubtree(List<List<Integer>> tree, int currentVertex, List<List<List<Integer>>> paths) {

        paths.get(currentVertex).add(Arrays.asList(currentVertex));

        for (Integer vertex : tree.get(currentVertex)) {
            countPathsInSubtree(tree, vertex, paths);
        }

        splice(tree, currentVertex, paths);
    }

    private static void splice(List<List<Integer>> tree, int currentVertex, List<List<List<Integer>>> paths) {
        List<Integer> adjacent = tree.get(currentVertex);
        int size = adjacent.size();

        for (int i = 0; i < size; i++) {
//            PathCombinations.countSubsets(i, adjacent, paths);
        }
    }


}
