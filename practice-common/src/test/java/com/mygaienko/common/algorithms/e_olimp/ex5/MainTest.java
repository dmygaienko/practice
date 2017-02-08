package com.mygaienko.common.algorithms.e_olimp.ex5;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;


/*
        if (k ==1 ) return 1;
        if (k ==2 ) return 4;
        if (k ==3 ) return 12;
        if (k ==4 ) return 24;
        if (k ==5 ) return 36;
        if (k ==6 ) return 60;
        if (k ==7 ) return 192;
        if (k ==8 ) return 120;
        if (k ==9 ) return 180;
        if (k ==10)  return 240;
        if (k ==11)  return 576;
        if (k ==12)  return 360;
        if (k ==13)  return 1296;
        if (k ==14)  return 900;
        if (k ==15)  return 720;
        if (k ==16)  return 840;
        if (k ==17)  return 9216;
        if (k ==18)  return 1260;
        if (k == 21) return 2880;
        if (k == 22) return 15360;
        if (k == 23) return 3600;
        if (k == 24) return 2520;
        if (k == 25) return 6480;
        if (k == 26) return 61440;
        if (k == 27) return 6300;
        if (k == 28) return 6720;
        if (k == 30) return 5040;
        if (k == 35) return 25920;
        if (k == 36) return 10080;
        if (k == 38) return 32400;
        if (k == 40) return 15120;
        if (k == 41) return 44100;
        if (k == 42) return 20160;
        if (k == 44) return 107520;
        if (k == 45) return 25200;
        if (k == 48) return 27720;
        if (k == 50) return 45360;
*/

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
    public void execute19() throws Exception {
        System.out.println(Main.execute(19));
    }

    @Test
    public void execute20() throws Exception {
        System.out.println(Main.execute(20));
    }

    @Test
    public void execute21() throws Exception {
        System.out.println(Main.execute(21));
    }


    @Test
    public void execute1_50() throws Exception {
        IntStream.range(1, 19).forEach(i -> System.out.println("k = " + i + "; " + "n = " + Main.execute(i)));
    }

    @Test
    public void execute21_50() throws Exception {
        IntStream.range(19, 29).forEach(i -> System.out.println("k = " + i + "; " + "n = " + Main.execute(i)));
    }

    @Test
    public void execute30_31() throws Exception {
        IntStream.range(30, 31).forEach(i -> System.out.println("k = " + i + "; " + "n = " + Main.execute(i)));
    }

    @Test
    public void execute32_34() throws Exception {
        IntStream.range(32, 34).forEach(i -> System.out.println("k = " + i + "; " + "n = " + Main.execute(i)));
    }

    @Test
    public void execute35_50() throws Exception {
        IntStream.range(50, 51).forEach(i -> System.out.println("k = " + i + "; " + "n = " + Main.execute(i)));
    }

}