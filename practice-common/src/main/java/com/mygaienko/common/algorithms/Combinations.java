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
            for (int i = 0; (subsetIndices[i] = i) < k - 1; i++);
            subsets.add(getSubset(input, subsetIndices));
            for(;;) {
                int i;
                // find position of item that can be incremented
                for (i = k - 1; i >= 0 && subsetIndices[i] == input.length - k + i; i--);
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
