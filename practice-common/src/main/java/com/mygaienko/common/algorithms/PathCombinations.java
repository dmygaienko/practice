package com.mygaienko.common.algorithms;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by dmygaenko on 13/02/2017.
 */
public class PathCombinations {

    public static List<List<Integer>> detectSubsets(int k, int currentVertex, List<Integer> adjacentVertices,
                                                    List<List<List<Integer>>> paths) {

        List<List<Integer>> newSubPaths = new ArrayList<>();

        int[] subsetIndices = new int[k];
        // here we'll keep indices
        // pointing to elements in input array

        if (k <= adjacentVertices.size()) {
            // first index sequence: 0, 1, 2, ...
            for (int i = 0; (subsetIndices[i] = i) < k - 1; i++) {
                System.out.println("i");
            }
            newSubPaths.addAll(generateSubset(subsetIndices, currentVertex, paths));

            for(;;) {
                int i;
                // find position of item that can be incremented
                // go from last index to first (right to left) if value is already max
                for (i = k - 1; i >= 0 && isMaxIndex(k, adjacentVertices, subsetIndices, i); i--) {
                    System.out.println("i");
                }
                if (i < 0) {
                    break;
                } else {
                    subsetIndices[i]++;                    // increment this item
                    for (++i; i < k; i++) {    // fill up remaining items
                        subsetIndices[i] = subsetIndices[i - 1] + 1;
                    }
                    newSubPaths.addAll(generateSubset(subsetIndices, currentVertex, paths));
                }
            }
        }

        return newSubPaths;
    }

    private static List<List<Integer>> generateSubset(int[] subsetIndices, int currentVertex,
                                                      List<List<List<Integer>>> paths) {
        List<List<Integer>> result;

        if (subsetIndices.length == 1) {
            List<List<Integer>> pathsToChild = paths.get(subsetIndices[0]+2);
            result = pathsToChild.stream()
                    .map((path) -> {
                                List<Integer> newPathFromV = new ArrayList<>(path);
                                newPathFromV.add(currentVertex);
                                return newPathFromV;
                            }
                    )
                    .collect(toList());
        } else {

            result = paths.get(subsetIndices[0]+2);
            for (int i = 1; i < subsetIndices.length; i++) {
                List<List<Integer>> nextPaths = paths.get(subsetIndices[i]+2);
                result = splicePairWise(result, nextPaths, i == 1 ? currentVertex : 0);
            }
        }
        return result;
    }

    private static List<List<Integer>> splicePairWise(List<List<Integer>> pathesThroughI1, List<List<Integer>> pathesThroughI2,
                                                      int currentVertex) {

        return pathesThroughI1.stream()
                        .flatMap(path -> pathesThroughI2.stream()
                                .map(pathI2 -> {
                                    List<Integer> newPath = new ArrayList<>();
                                    newPath.addAll(path);
                                    newPath.addAll(pathI2);
                                    if (currentVertex != 0) newPath.add(currentVertex);
                                    return newPath;
                                })
                        ).collect(toList());
    }

    private static boolean isMaxIndex(int k, List<Integer> adjacentVertices, int[] subsetIndices, int i) {
        return subsetIndices[i] == adjacentVertices.size() - k + i;
    }

}
