package com.mygaienko.common.algorithms.condingame.hard.power_of_thor_episode2;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by enda1n on 03.04.2017.
 */
public class PlayerTest {
    @Test
    public void main() throws Exception {

    }

    @Test
    public void countCentroid() throws Exception {
        System.out.println(Player.countCentroid(Arrays.asList(
                new Player.Position(1, 1),
                new Player.Position(11, 1),
                new Player.Position(11, 11),
                new Player.Position(1, 11)
        )));
    }

}