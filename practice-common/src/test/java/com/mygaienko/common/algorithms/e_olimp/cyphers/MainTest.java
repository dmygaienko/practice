package com.mygaienko.common.algorithms.e_olimp.cyphers;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by enda1n on 30.01.2017.
 */
public class MainTest {

    @Test
    public void getCyphers21() throws Exception {
        assertEquals("2 1", Main.getCyphers(21));
    }

    @Test
    public void getCyphers54321() throws Exception {
        assertEquals("5 4 3 2 1", Main.getCyphers(54321));
    }

    @Test
    public void getCyphers7654321() throws Exception {
        assertEquals("7 6 5 4 3 2 1", Main.getCyphers(7654321));
    }

}