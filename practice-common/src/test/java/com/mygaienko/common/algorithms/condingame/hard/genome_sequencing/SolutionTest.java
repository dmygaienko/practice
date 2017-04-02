package com.mygaienko.common.algorithms.condingame.hard.genome_sequencing;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by enda1n on 02.04.2017.
 */
public class SolutionTest {

    @Test
    public void main() throws Exception {
        String inputs = getGameInputString4();
        System.setIn(new ByteArrayInputStream(inputs.getBytes(StandardCharsets.UTF_8)));
        Solution.main(new String[]{});
    }

    private String getGameInputString4() {
        return "2\n" +
                "AGATTA\n" +
                "GAT\n";
    }

    @Test
    public void testAddNextString() throws Exception {
        ArrayList<List<String>> shuffled = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>(Arrays.asList("CCCTG", "TGACA", "CATGA"));
        Solution.addNextString(0, new LinkedHashSet<>(), strings, shuffled);
    }

    @Test
    public void test() throws Exception {
        Solution.getCommonSubString("CCCTGACA", "CATGA");

    }

    @Test
    public void test4() throws Exception {
        Solution.getCommonSubString("AGATTA", "GAT");

    }

    public String getGameInputString7() {
        return "3\n" +
                "CCCTG\n" +
                "TGACA\n" +
                "CATGA\n";
    }
}