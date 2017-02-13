package com.mygaienko.common.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmygaenko on 13/02/2017.
 */
public class Combinations {

    public static List<int[]> countSubsets(int k, int[] input) {

        List<int[]> subsets = new ArrayList<>();

        int[] subsetIndices = new int[k];                  // here we'll keep indices
        // pointing to elements in input array

        if (k <= input.length) {
            // first index sequence: 0, 1, 2, ...
            for (int i = 0; (subsetIndices[i] = i) < k - 1; i++) {
                System.out.println("i");
            }
            subsets.add(getSubset(input, subsetIndices));
            for(;;) {
                int i;
                // find position of item that can be incremented
                // go from last index to first (right to left) if value is already max
                for (i = k - 1; i >= 0 && isMaxIndex(k, input, subsetIndices, i); i--) {
                    System.out.println("i");
                }
                if (i < 0) {
                    break;
                } else {
                    subsetIndices[i]++;                    // increment this item
                    for (++i; i < k; i++) {    // fill up remaining items
                        subsetIndices[i] = subsetIndices[i - 1] + 1;
                    }
                    subsets.add(getSubset(input, subsetIndices));
                }
            }
        }

        return subsets;
    }

    private static boolean isMaxIndex(int k, int[] input, int[] subsetIndices, int i) {
        return subsetIndices[i] == input.length - k + i;
    }

    // generate actual subset by index sequence
    static int[] getSubset(int[] input, int[] subset) {
        int[] result = new int[subset.length];
        for (int i = 0; i < subset.length; i++)
            result[i] = input[subset[i]];
        return result;
    }

    @Test
    public void testCountSubsets() {
        int[] input = {10, 20, 30, 40, 50};    // input array
        countSubsets(3, input);
    }

}
