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
        assertEquals(20, Main.execute(2));
        assertEquals(28, Main.execute(3));
        assertEquals(54, Main.execute(8));
        assertEquals(62, Main.execute(9));
        assertEquals(67, Main.execute(10));
        assertEquals(72, Main.execute(11));
        assertEquals(138, Main.execute(25));
        assertEquals(141, Main.execute(26));
        assertEquals(144, Main.execute(27));
        assertEquals(162, Main.execute(30));
        assertEquals(300, Main.execute(64));
    }

    @Test
    public void test10Execute() throws Exception {
        assertEquals(67, Main.execute(10));
        assertEquals(72, Main.execute(11));
    }

    @Test
    public void test30Execute() throws Exception {
        assertEquals(162, Main.execute(30));
    }

    @Test
    public void test3Execute() throws Exception {
        assertEquals(28, Main.execute(3));
    }

    @Test
    public void test26Execute() throws Exception {
        assertEquals(133, Main.execute(24));
        assertEquals(138, Main.execute(25));
        assertEquals(141, Main.execute(26));
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

    @Test
    public void testMath() throws Exception {
        double side = new Double(Math.pow(8, 1 / 2d)).intValue();
        System.out.println(side);
    }
}