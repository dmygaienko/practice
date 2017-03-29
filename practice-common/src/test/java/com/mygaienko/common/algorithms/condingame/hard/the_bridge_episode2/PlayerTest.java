package com.mygaienko.common.algorithms.condingame.hard.the_bridge_episode2;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by dmygaenko on 29/03/2017.
 */
public class PlayerTest {

    @Test
    public void test() throws Exception {
        String inputs = getInputString();
        System.setIn(new ByteArrayInputStream(inputs.getBytes(StandardCharsets.UTF_8)));
        Player.main(new String[]{});
    }

    private String getInputString() {
        return  "1\n" +
                "1\n" +
                "........................................\n" +
                "........................................\n" +
                "...........0............................\n" +
                "........................................\n" +
                "0\n" +
                "0\n" +
                "1\n"
                ;
    }
}