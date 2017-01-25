package com.mygaienko.common.algorithms.e_olimp.ex2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by enda1n on 25.01.2017.
 */
public class MainTest {

    @Test
    public void testExecute() throws Exception {
        assertEquals("5", Main.execute(12345));
        assertEquals("4", Main.execute(3450));
        assertEquals("1", Main.execute(0));
        assertEquals("1", Main.execute(1));
        assertEquals("2", Main.execute(10));
        assertEquals("9", Main.execute(100000000));
    }
}
