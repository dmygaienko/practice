package com.mygaienko.common.collection;


import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;

/**
 * Created by dmygaenko on 09/11/2016.
 */
public class RangeTest {

    @Test
    public void test() {
        Set<Integer> indexesByRange = Range.getIndexesByRange("1-3, 3, 4, 5");
        assertThat(indexesByRange, hasItems(1, 2, 3, 4, 5));
        System.out.println(indexesByRange);
    }
}
