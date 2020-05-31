package com.mygaienko.common.algorithms.e_olimp.ex3;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by enda1n on 25.01.2017.
 */
public class MainTest {


    @Test
    public void testPow() throws Exception {

        System.out.println(Math.pow(1, 3));
        System.out.println(Math.pow(2, 3));
        System.out.println(Math.pow(3, 3));
        System.out.println(Math.pow(4, 3));
        System.out.println(Math.pow(5, 3));
        System.out.println(Math.pow(6, 3));
        System.out.println(Math.pow(7, 3));
        System.out.println(Math.pow(8, 3));
        System.out.println(Math.pow(9, 3));
        System.out.println(Math.pow(10, 3));

    }

    @Test
    public void testPow1_n() throws Exception {
        double i = 3;

        System.out.println(Math.pow(1025, 1/i));
        System.out.println(Math.pow(1100, 1/i));

    }

    @Test
    public void testExecute() throws Exception {
        assertEquals(0, Main.execute(0));
        assertEquals(12, Main.execute(1));
        assertEquals(54, Main.execute(8));
        assertEquals(144, Main.execute(27));
    }

    @Test
    public void testGlue() throws Exception {
        int[] xyz = new int[] {1, 1, 2};
        System.out.println(((xyz[0]-1) * xyz[2]) - (xyz[0] * (xyz[2]-1)));
        assertEquals(7, xyz[0] * xyz[2] * 4 - ((xyz[0]-1) * xyz[2]) - (xyz[0] * (xyz[2]-1)));
    }


    @Test
    public void testCollectGreaterCub() throws Exception {
        int[] xyz = new int[] {2, 2, 2};
        System.out.println(Main.collectGreaterCub(xyz));
    }
}