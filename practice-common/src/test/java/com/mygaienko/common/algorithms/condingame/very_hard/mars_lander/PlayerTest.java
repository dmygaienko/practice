package com.mygaienko.common.algorithms.condingame.very_hard.mars_lander;

import org.junit.Test;

/**
 * Created by enda1n on 15.04.2017.
 */
public class PlayerTest {

    @Test
    public void testCalculateCurrentDirection() throws Exception {
        double v = Player.calculateCurrentDirection(10, 5);
        System.out.println(v);
    }

    @Test
    public void testCalculateCurrentDirection1() throws Exception {
        double v = Player.calculateCurrentDirection(10, -5);
        System.out.println(v);
    }

    @Test
    public void testCalculateCurrentDirection2() throws Exception {
        double v = Player.calculateCurrentDirection(-10, -5);
        System.out.println(v);
    }

    @Test
    public void testCalculateCurrentDirection3() throws Exception {
        double v = Player.calculateCurrentDirection(-10, 5);
        System.out.println(v);
    }

    @Test
    public void testCalculateDesiredDirection() throws Exception {
        double v = Player.calculateDesiredDirection(new Player.Point(0, 0), new Player.Point(1, 1));
        System.out.println(v);
    }
}