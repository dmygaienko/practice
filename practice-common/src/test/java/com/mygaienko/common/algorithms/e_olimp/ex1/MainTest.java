package com.mygaienko.common.algorithms.e_olimp.ex1;

import com.mygaienko.common.algorithms.e_olimp.ex1.Main;
import org.junit.Test;

/**
 * Created by enda1n on 25.01.2017.
 */
public class MainTest {

    @Test
    public void test1() throws Exception {
        Main.simple(1);
        Main.simple(99);
        Main.simple(23);
        Main.simple(0);
        Main.simple(10);
        Main.simple(50);

        Main.simple(9);
        Main.simple(100);
    }

    @Test
    public void test23() throws Exception {
        Main.simple(23);
    }
}