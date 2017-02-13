package com.mygaienko.common.algorithms.e_olimp.ex431;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by enda1n on 14.02.2017.
 */
public class MainTest {

    @Test
    public void name() throws Exception {
        List<List<Integer>> tree = new ArrayList<>();
        tree.add(0, Collections.emptyList());
        tree.add(1, Arrays.asList(2, 3, 4));
        tree.add(2, Collections.emptyList());
        tree.add(3, Collections.emptyList());
        tree.add(4, Collections.emptyList());
        List<List<List<Integer>>> paths = new ArrayList<>();
        paths.add(0, Collections.emptyList());
        paths.add(1, new ArrayList<>());
        paths.add(2, new ArrayList<>());
        paths.add(3, new ArrayList<>());
        paths.add(4, new ArrayList<>());
        Main.countPathsInSubtree(tree, 1, paths);

    }
}