package com.mygaienko.common.algorithms.condingame.hard.roller_coaster;

import com.mygaienko.common.algorithms.condingame.hard.the_bridge_episode2.Player;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

/**
 * Created by enda1n on 30.03.2017.
 */
public class SolutionTest {
    @Test
    public void test() throws Exception {
        //String inputs = getInputString();
        //String inputs = getGameInputString();
        String inputs = getGameInput();
        System.setIn(new ByteArrayInputStream(inputs.getBytes(StandardCharsets.UTF_8)));
        Solution.main(new String[]{});
    }

    public String getGameInput() {
        return "5\n3\n4\n" +
                "2\n" +
                "3\n" +
                "5\n" +
                "3";
    }
}