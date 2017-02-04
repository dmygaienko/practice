package com.mygaienko.common.algorithms.e_olimp.ex002;

import org.h2.util.StringUtils;

import java.io.*;
import java.math.BigDecimal;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.*;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Math.toIntExact;

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
        int batchSize = 1000;
        int bytesPerBatch = bytesPerLine * batchSize;

        File bulkFile = new File(fileName);
        int batches = countBatches(bytesPerBatch, bulkFile);

        System.out.println(batches);

        FileChannel bulkChannel = new RandomAccessFile(bulkFile, "r").getChannel();

        batchProcessing(batches, i -> {
            try {
                processBulkBatch(bulkChannel, i, bytesPerLine, batchSize);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        });

        return null;
    }

    private static int countBatches(int bytesPerBatch, File file) {
        int batches = toIntExact(file.length() / bytesPerBatch) ;
        if (file.length() % bytesPerBatch > 0) batches++;
        return batches;
    }

    private static void batchProcessing(int batches, IntConsumer consumer) {
        IntStream
                .range(0, batches)
                .forEach(consumer);
    }

    private static void processBulkBatch(FileChannel channel, int i, int bytesPerLine, int batchSize) throws IOException {
        TreeMap<String, BigDecimal> groupedBatch = groupBatch(channel, i, bytesPerLine, batchSize);

        String previousFileName = "analyzed/groupedBatch" + (i - 1) + ".dat";
        String newFileName = "analyzed/groupedBatch" + i + ".dat";

        File prevFile = new File(previousFileName);
        if (prevFile.exists()) {

            try (RandomAccessFile raf = new RandomAccessFile(prevFile, "r");
                 FileChannel previousFileChannel = raf.getChannel()) {
                mergeGroupedBatchWithFile(groupedBatch, previousFileChannel, bytesPerLine, batchSize, prevFile, newFileName);
            }
            prevFile.deleteOnExit();
        } else {
            dumpToFile(groupedBatch, newFileName);
        }
    }

    private static void mergeGroupedBatchWithFile(TreeMap<String, BigDecimal> groupedBatch, FileChannel channel, int bytesPerLine,
                                                  int batchSize, File prevFile, String newFileName) throws IOException {

        int bytesPerBatch = bytesPerLine * batchSize;
        int batches = countBatches(bytesPerBatch, prevFile);

        batchProcessing(batches,
                i -> {
                    try {
                        int currentPosition = bytesPerBatch * i;
                        long bufferSize = getBufferSize(channel.size(), currentPosition, bytesPerBatch);

                        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, bytesPerBatch * i, bufferSize);

                        TreeMap<String, BigDecimal> fileMap = readBufferToMap(buffer, bytesPerLine, toIntExact(bufferSize / bytesPerLine));

                        SortedMap<String, BigDecimal> headGroupedBatch = groupedBatch.headMap(fileMap.lastEntry().getKey());
                        TreeMap<String, BigDecimal> mergedMap = mergeMaps(headGroupedBatch, fileMap);

                        dumpToFile(mergedMap, newFileName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    private static TreeMap<String, BigDecimal> mergeMaps(SortedMap<String, BigDecimal> headGroupedBatch, TreeMap<String, BigDecimal> fileMap) {
        return Stream.of(headGroupedBatch, fileMap)
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                BigDecimal::add,
                                TreeMap::new
                        ));
    }

    private static void dumpToFile(TreeMap<String, BigDecimal> groupedBatch, String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }

        try (FileWriter out = new FileWriter(fileName, true);
             BufferedWriter bw = new BufferedWriter(out)) {
            groupedBatch.forEach((key, value) -> {
                try {
                    bw.write(getPaddedString(key, value));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static String getPaddedString(String key, BigDecimal value) {
        return StringUtils.pad(key, 15, " ", true) + ": " + StringUtils.pad(value.toString(), 15, " ", true) + "\n";
    }

    private static TreeMap<String, BigDecimal> readBufferToMap(MappedByteBuffer buffer, int bytesPerLine, int batchSize) {
        TreeMap<String, BigDecimal> result = IntStream
                .range(0, batchSize)
                .mapToObj((line) -> {
                    byte[] bytes = new byte[bytesPerLine];
                    buffer.get(bytes, 0, 33);
                    return new String(bytes, Charset.defaultCharset());
                })
                .map((str) -> str.split(":"))
                .collect(Collectors.toMap(
                        (array) -> array[0].trim(), (array) -> new BigDecimal(array[1].trim()), BigDecimal::add, TreeMap::new));
        buffer.clear();
        return result;
    }

    private static TreeMap<String, BigDecimal> groupBatch(FileChannel channel, int i, int bytesPerLine, int batchSize) throws IOException {
        int bytesPerBatch = batchSize * bytesPerLine;

        int currentPosition = bytesPerBatch * i;
        long bufferSize = getBufferSize(channel.size(), currentPosition, bytesPerBatch);

        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, currentPosition, bufferSize);

        TreeMap<String, BigDecimal> result = IntStream
                .range(0, toIntExact(bufferSize/bytesPerLine))
                .mapToObj((line) -> {
                    byte[] bytes = new byte[bytesPerLine];
                    buffer.get(bytes, 0, 33);
                    return new String(bytes, Charset.defaultCharset());
                })
                .map((str) -> str.split(":"))
                .collect(Collectors.toMap(
                        (array) -> array[0].trim(), (array) -> new BigDecimal(array[1].trim()), BigDecimal::add, TreeMap::new));

        System.out.println(result);
        return result;
    }

    private static long getBufferSize(long fileSize, int currentPosition, int bytesPerBatch) throws IOException {
        long bufferSize = bytesPerBatch;

        if (fileSize < currentPosition + bytesPerBatch) {
            bufferSize =  fileSize - currentPosition;
        }
        return bufferSize;
    }

}
