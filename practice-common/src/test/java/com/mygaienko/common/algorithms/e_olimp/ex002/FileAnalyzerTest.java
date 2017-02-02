package com.mygaienko.common.algorithms.e_olimp.ex002;

import org.h2.util.StringUtils;
import org.junit.Test;

import java.io.*;
import java.math.BigDecimal;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;


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
        FileAnalyzer.analyze("bigFile.dat");

      /*  try (RandomAccessFile randomAccessFile = new RandomAccessFile(testFile, "rw")) {
            randomAccessFile.skipBytes(33);
            System.out.println(randomAccessFile.readLine());
        }*/
    }

}