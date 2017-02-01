package com.mygaienko.common.algorithms.e_olimp.ex001;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by dmygaenko on 01/02/2017.
 */
public class HonestDividerTest {

    @Test
    public void divide2on3() throws Exception {
        assertEquals(Arrays.asList(1, 1, 0), HonestDivider.divide(2, 3));
    }

    @Test
    public void divide7on5() throws Exception {
        assertEquals(Arrays.asList(2, 2, 1, 1, 1), HonestDivider.divide(7, 5));
    }

    @Test
    public void divide9on5() throws Exception {
        assertEquals(Arrays.asList(2, 2, 2, 2, 1), HonestDivider.divide(9, 5));
    }

    @Test
    public void divide17on5() throws Exception {
        assertEquals(Arrays.asList(4, 4, 3, 3, 3), HonestDivider.divide(17, 5));
    }

}