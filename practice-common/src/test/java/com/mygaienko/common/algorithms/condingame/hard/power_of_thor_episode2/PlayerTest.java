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
    @Test
    public void countCentroid10() throws Exception {
        System.out.println(
                Player.countCentroid(Arrays.asList(
                        new Player.Position(0, 9),

                        new Player.Position(25, 9),
                        new Player.Position(26, 9),
                        new Player.Position(27, 9),
                        new Player.Position(28, 9),
                        new Player.Position(29, 9),
                        new Player.Position(30, 9),
                        new Player.Position(31, 9),
                        new Player.Position(32, 9),
                        new Player.Position(33, 9),
                        new Player.Position(34, 9),
                        new Player.Position(35, 9),
                        new Player.Position(36, 9),
                        new Player.Position(37, 9),
                        new Player.Position(38, 9),
                        new Player.Position(39, 9)
                        )
                )
        );
    }

}