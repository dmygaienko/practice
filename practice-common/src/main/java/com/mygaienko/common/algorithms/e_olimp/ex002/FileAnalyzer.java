package com.mygaienko.common.algorithms.e_olimp.ex002;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by enda1n on 01.02.2017.
 */
public class FileAnalyzer {



    public File analyze(String fileName) throws IOException {

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


        return null;
    }
















}


    /*public File analyze(String fileName) throws IOException {
    private int bytesPerLine = 33;

    RandomAccessFile raf = new RandomAccessFile(new File(fileName), "r");
        raf.skipBytes(1000*33);
        raf.readLine();
        FileChannel channel = raf.getChannel();

        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, 1000*33);
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream
                    .map((str) -> str.split(":"))
                    .collect(Collectors.toMap(ar -> ar[0], ar -> ar[1]));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/