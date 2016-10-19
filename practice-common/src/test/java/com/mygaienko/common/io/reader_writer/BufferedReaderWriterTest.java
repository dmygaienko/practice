package com.mygaienko.common.io.reader_writer;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;


/**
 * Created by enda1n on 18.10.2016.
 */
public class BufferedReaderWriterTest {

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
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file))) {
            fileWriter.write("中文");
            fileWriter.newLine();
            fileWriter.write(new char[]{'c', 'h', 'a', 'r'});
            fileWriter.flush();
        }

        try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
            assertEquals("中文", fileReader.readLine());
            assertEquals("char", fileReader.readLine());
        }
    }

}
