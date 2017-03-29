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
        //String inputs = getInputString();
        String inputs = getGameInputString();
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
                //1st iteration
                "0\n" +
                "0\n" +
                "2\n" +
                "1\n" +
                //2nd iteration
                "1\n" +
                "1\n" +
                "2\n" +
                "1\n" +
                //3rd
                "2\n" +
                "3\n" +
                "2\n" +
                "1\n" +
                //4th iteration
                "2\n" +
                "5\n" +
                "2\n" +
                "1\n"
                ;
    }

    private String getGameInputString() {
        return getMotoStrings() + getBridgeStrings() +
                //1st iteration
                "3\n" +
                "30\n" +
                "1\n" +
                "1\n" +

                "3\n" +
                "30\n" +
                "1\n" +
                "1\n" +

                "3\n" +
                "30\n" +
                "2\n" +
                "1\n" +

                "3\n" +
                "30\n" +
                "3\n" +
                "1\n";
    }

    private String getBridgeStrings() {
        return
                "........................................\n" +
                "........................................\n" +
                "...........0............................\n" +
                "........................................\n";
    }

    private String getMotoStrings() {
        return "4\n" +
                "4\n";
    }
}