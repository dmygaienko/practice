package com.mygaienko.common.io_nio.reader_writer;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;


/**
 * Created by enda1n on 18.10.2016.
 */
public class StreamReaderWriterTest {

    private File file;

    @Before
    public void setUp() throws Exception {
        file = new File("StreamReaderWriterTest.dat");
        file.delete();
        file.createNewFile();
        file.deleteOnExit();
    }

    @Test
    public void test() throws Exception {
        try (OutputStreamWriter outputStream = new OutputStreamWriter(new FileOutputStream(file))) {
            outputStream.write("中文");
            outputStream.write(new char[]{'c', 'h', 'a', 'r'});
        }

        try (InputStreamReader inputStream = new InputStreamReader(new FileInputStream(file))) {
            char[] chars = new char[100];
            assertEquals(6, inputStream.read(chars));
            System.out.println(Arrays.toString(chars));
        }
    }

    @Test
    public void testWitUtfEncoding() throws Exception {
        try (OutputStreamWriter outputStream = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_16)) {
            outputStream.write("строкатый");
            outputStream.write(new char[]{'ъ', 'ё', 'a', 'r'});
        }

        try (InputStreamReader inputStream = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_16)) {
            char[] chars = new char[100];
            assertEquals(13, inputStream.read(chars));
            System.out.println(Arrays.toString(chars));
        }
    }

    @Test
    public void testWithAsciiEncoding() throws Exception {
        try (OutputStreamWriter outputStream = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.US_ASCII)) {
            outputStream.write("中文");
            outputStream.write(new char[]{'ъ', 'ё', 'a', 'r'});
        }

        try (InputStreamReader inputStream = new InputStreamReader(new FileInputStream(file), StandardCharsets.US_ASCII)) {
            char[] chars = new char[100];
            assertEquals(6, inputStream.read(chars));
            System.out.println(Arrays.toString(chars));
        }
    }
}
