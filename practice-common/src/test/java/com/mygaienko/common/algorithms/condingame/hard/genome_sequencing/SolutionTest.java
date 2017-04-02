package com.mygaienko.common.algorithms.condingame.hard.genome_sequencing;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by enda1n on 02.04.2017.
 */
public class SolutionTest {

    @Test
    public void main() throws Exception {
        String inputs = getGameInputString7();
        System.setIn(new ByteArrayInputStream(inputs.getBytes(StandardCharsets.UTF_8)));
        Solution.main(new String[]{});
    }

    @Test
    public void test() throws Exception {
        Solution.getCommonSubString("CCCTGACA", "CATGA");

    }

    public String getGameInputString7() {
        return "3\n" +
                "CCCTG\n" +
                "TGACA\n" +
                "CATGA\n";
    }
}