package com.mygaienko.common.algorithms.e_olimp.ex5;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Created by enda1n on 08.02.2017.
 */
public class MainTest {

    @Test
    public void execute1() throws Exception {
        System.out.println(Main.execute(1));
    }

    @Test
    public void execute2() throws Exception {
        System.out.println(Main.execute(2));
    }

    @Test
    public void execute3() throws Exception {
        System.out.println(Main.execute(3));
    }

    @Test
    public void execute4() throws Exception {
        System.out.println(Main.execute(4));
    }

    @Test
    public void execute5() throws Exception {
        System.out.println(Main.execute(5));
    }

    @Test
    public void execute6() throws Exception {
        System.out.println(Main.execute(6));
    }

    @Test
    public void execute1_50() throws Exception {
        IntStream.range(1, 51).forEach(i -> System.out.println("k = " + i + "; " + "n = " + Main.execute(i)));
    }

}