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
    }
}