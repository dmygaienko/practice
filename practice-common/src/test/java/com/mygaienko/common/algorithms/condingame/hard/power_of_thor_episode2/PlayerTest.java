package com.mygaienko.common.algorithms.condingame.hard.power_of_thor_episode2;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by enda1n on 03.04.2017.
 */
public class PlayerTest {
    @Test
    public void test5() throws Exception {
        String inputs = getInput51();
        System.setIn(new ByteArrayInputStream(inputs.getBytes(StandardCharsets.UTF_8)));
        Player.main(new String[]{});
    }

    private String getInput51() {
        return "15 3\n" +           "2\n" + "15\n" +

                "13 6\n" +
                "10 10\n" +
                "8 1\n" +
                "17 6\n" +
                "30 7\n" +
                "16 5\n" +
                "20 4\n" +
                "11 6\n" +
                "16 6\n" +
                "15 5\n" +
                "24 4\n" +
                "23 9\n" +
                "31 8\n" +
                "16 4\n" +
                "14 7";
    }

    private String getInput5() {
        return "20 9\n" +

                "2\n" +
                "15\n" +
                //giants 1 move
                "5 9\n" +
                "2 17\n" +
                "0 0\n" +
                "24 9\n" +
                "38 7\n" +
                "20 12\n" +
                "28 2\n" +
                "3 8\n" +
                "12 11\n" +
                "11 11\n" +
                "32 4\n" +
                "31 17\n" +
                "39 8\n" +
                "10 4\n" +
                "8 15\n" +
                //giants 2 move
                "2\n" +
                "15\n" +

                "6 9\n" +
                "3 17\n" +
                "1 0\n" +
                "23 9\n" +
                "37 7\n" +
                "20 11\n" +
                "27 3\n" +
                "4 8\n" +
                "13 11\n" +
                "12 11\n" +
                "31 5\n" +
                "30 16\n" +
                "38 8\n" +
                "11 5\n" +
                "9 15\n" +
                //giants 3 move
                "2\n" +
                "15\n" +

                "7 9\n" +
                "4 17\n" +
                "2 1\n" +
                "23 10\n" +
                "36 7\n" +
                "21 11\n" +
                "26 4\n" +
                "5 8\n" +
                "14 11\n" +
                "13 11\n" +
                "30 6\n" +
                "29 15\n" +
                "37 8\n" +
                "12 6\n" +
                "10 15\n"


                ;
    }

    @Test
    public void main() throws Exception {
        Map<Integer, Map<Integer, Player.Position>> integerMapMap = Player.initGiantsMap(Arrays.asList(
                new Player.Position(25, 9),
                new Player.Position(25, 10),
                new Player.Position(25, 11),
                new Player.Position(26, 9),
                new Player.Position(27, 9),
                new Player.Position(28, 9)
        ));
        System.out.println(integerMapMap);
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
    public void countCentroid2() throws Exception {
        System.out.println(Player.countCentroid(Arrays.asList(
                new Player.Position(29, 17),
                new Player.Position(28, 17),
                new Player.Position(18, 12)
        )));
    }

    @Test
    public void countCentroid12() throws Exception {
        System.out.println(Player.countCentroid(Arrays.asList(
                new Player.Position(5, 9),
                new Player.Position(2, 17),
                new Player.Position(0, 0),
                new Player.Position(24, 9),
                new Player.Position(38, 7),
                new Player.Position(20, 12),
                new Player.Position(28, 2),
                new Player.Position(3, 8),
                new Player.Position(12, 11),
                new Player.Position(11, 11),
                new Player.Position(32, 4),
                new Player.Position(20, 9)
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

    @Test
    public void countCentroidRev10() throws Exception {
        System.out.println(
                Player.countCentroid(Arrays.asList(
                        new Player.Position(9, 0),

                        new Player.Position(9, 25),
                        new Player.Position(9, 26),
                        new Player.Position(9, 27),
                        new Player.Position(9, 28),
                        new Player.Position(9, 29),
                        new Player.Position(9, 30),
                        new Player.Position(9, 31),
                        new Player.Position(9, 32),
                        new Player.Position(9, 33),
                        new Player.Position(9, 34),
                        new Player.Position(9, 35),
                        new Player.Position(9, 36),
                        new Player.Position(9, 37),
                        new Player.Position(9, 38),
                        new Player.Position(9, 39)
                        )
                )
        );
    }

}