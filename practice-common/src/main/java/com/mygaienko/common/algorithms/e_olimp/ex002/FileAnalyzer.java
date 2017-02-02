package com.mygaienko.common.algorithms.e_olimp.ex002;

import java.io.*;
import java.math.BigDecimal;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

/**
 * Created by enda1n on 01.02.2017.
 */
public class FileAnalyzer {

    /*
            read big file with RandomAccessFile
            in for i loop read file by batches:
                 1. count position considering:
                       - max length of batches
                       - user id and expense are padded values to 20 length to count max length of line

                 2. read bytes into MappedByteBuffer from counted position to (counted position + max length of batch)
                 3. from buffer read lines and group into tree map to have sorted content
                 4. content of tree map save to new File
                    - in case file exists from previous loop:
                           - read file using RandomAccessFile by batches
                           - merge tree map with content from the file:
                                   - for matched user id summarize expenses
                                   - if only biggest user ids remain from tree map then read another part from file and merge

           return the last merged file
    */

    public static File analyze(String fileName) throws IOException {
        int bytesPerLine = 33;
        int batch = 1000;

        RandomAccessFile raf = new RandomAccessFile(new File(fileName), "r");
      /*  raf.skipBytes(batch*bytesPerLine);
        raf.readLine();*/
        FileChannel channel = raf.getChannel();

        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, batch*bytesPerLine);

        TreeMap<String, BigDecimal> collect = IntStream
                .range(0, batch)
                .mapToObj((i) -> {
                    byte[] bytes = new byte[bytesPerLine];
                    buffer.get(bytes, 0, 33);
                    return new String(bytes, Charset.defaultCharset());
                })
                .map((str) -> str.split(":"))
                .collect(Collectors.toMap(
                        (array) -> array[0].trim(), (array) -> new BigDecimal(array[1].trim()), BigDecimal::add, TreeMap::new));

        System.out.println(collect);

                /*.collect(
                        Collectors.groupingBy(
                                (array) -> array[0],
                                );*/

        /*

        (array) -> array[0],
                        (array) -> array[1],
                        () -> new TreeMap<String, String>(),
                        (v1, v2) -> {v1 + v2})


        for (int i = 0; i < batch; i++) {
            byte[] bytes = new byte[bytesPerLine];
            buffer.get(bytes, 0, 33);
            System.out.println(new String(bytes, Charset.defaultCharset()));
        }

        * */
        return null;
    }

}
