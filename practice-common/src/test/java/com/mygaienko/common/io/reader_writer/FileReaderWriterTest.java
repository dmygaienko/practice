package com.mygaienko.common.io.reader_writer;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;


/**
 * Created by enda1n on 18.10.2016.
 */
public class FileReaderWriterTest {

    private File file;

    @Before
    public void setUp() throws Exception {
        file = new File("FileReaderWriterTest.dat");
        file.delete();
        file.createNewFile();
        file.deleteOnExit();
    }

    @Test
    public void test() throws Exception {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write("中文");
            fileWriter.write(new char[]{'c', 'h', 'a', 'r'});
        }

        try (FileReader fileReader = new FileReader(file)) {
            char[] chars = new char[100];
            assertEquals(6, fileReader.read(chars));
            System.out.println(Arrays.toString(chars));
        }
    }

    @Test
    public void testWitUtfEncoding() throws Exception {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write("строкатый");
            fileWriter.write(new char[]{'ъ', 'ё', 'a', 'r'});
        }

        try (FileReader fileReader = new FileReader(file)) {
            char[] chars = new char[100];
            assertEquals(13, fileReader.read(chars));
            System.out.println(Arrays.toString(chars));
        }
    }

}
