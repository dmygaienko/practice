package com.mygaienko.common.algorithms.condingame.hard.vox_codei;

import com.mygaienko.common.algorithms.condingame.hard.the_bridge_episode2.*;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

/**
 * Created by enda1n on 01.04.2017.
 */
public class PlayerTest {

    @Test
    public void main() throws Exception {
        String inputs = getGameInputString1();
        System.setIn(new ByteArrayInputStream(inputs.getBytes(StandardCharsets.UTF_8)));
        Player.main(new String[]{});
    }

    private String getGameInputString1() {
        return "4 3\n" +

                "....\n" +
                ".@..\n" +
                "....\n" +

                "15 1";
    }

}