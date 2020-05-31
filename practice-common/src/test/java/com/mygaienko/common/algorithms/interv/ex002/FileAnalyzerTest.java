package com.mygaienko.common.algorithms.interv.ex002;

import org.h2.util.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;


/**
 * Created by enda1n on 01.02.2017.
 */
public class FileAnalyzerTest {

    @Test
    public void test() throws IOException {
        String testFile = "bigFile.dat";
        try (PrintWriter printWriter = new PrintWriter(new File(testFile))) {
            for (int i = 0; i < 100000; i++) {
                String userId = "userId_" + new Random().nextInt(1000);
                String expense = new BigDecimal(new Random().nextDouble() * 100000).setScale(2, BigDecimal.ROUND_DOWN).toString();
                printWriter.print(StringUtils.pad(userId, 15, " ", true) + ": " + StringUtils.pad(expense, 15, " ", true) + "\n");
            }
        }
    }

    @Test
    public void testAnalyze() throws IOException {
        assertEquals("analyzed/groupedBatch99.dat", FileAnalyzer.analyze("bigFile.dat"));

      /*  try (RandomAccessFile randomAccessFile = new RandomAccessFile(testFile, "rw")) {
            randomAccessFile.skipBytes(33);
            System.out.println(randomAccessFile.readLine());
        }*/
    }

    @Test
    public void testCurrentPath() throws IOException {
        System.out.println(Paths.get("").toAbsolutePath());
    }

    @Test
    public void testSubMap() throws IOException {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
      /*  map.put("4", "4");*/
        map.put("5", "5");
        map.put("6", "6");
        map.put("7", "7");
        map.put("8", "8");

        System.out.println(map.subMap("0", "4"));
        System.out.println(map.headMap("4"));
        System.out.println(map.tailMap("4"));
        System.out.println(map);
    }


    @Test
    public void mergeMaps() {
        TreeMap<String, String> m1 = getTreeMap(0, 5);
        TreeMap<String, String> m2 = getTreeMap(4, 7);

        TreeMap<String, String> merged = Stream.of(m1, m2)
                .map(Map::entrySet)          // converts each map into an entry set
                .flatMap(Collection::stream) // converts each set into an entry stream, then
                // "concatenates" it in place of the original set
                .collect(
                        Collectors.toMap(        // collects into a map
                                Map.Entry::getKey,   // where each entry is based
                                Map.Entry::getValue, // on the entries in the stream
                                (v1, v2) -> v1 + v2,
                                TreeMap::new
                        ));
        System.out.println(merged);
    }

    @Test
    public void testGetTreeMap() {
        System.out.println(getTreeMap(0, 5));
    }

    private TreeMap<String, String> getTreeMap(int i, int i1) {
        return IntStream
                .range(i, i1)
                .boxed()
                .collect(Collectors.toMap(String::valueOf, String::valueOf, (v1, v2) -> v1 + v2, TreeMap::new));
    }

}