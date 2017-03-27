package com.mygaienko.common.algorithms;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by enda1n on 27.03.2017.
 */
public class PermutationsTest {

    @Test
    public void testPermutation() throws Exception {
        List<List<Integer>> permute = Permutations.permute(new int[]{0, 1, 2, 3});

        System.out.println(permute);

    }
}