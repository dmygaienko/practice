package com.mygaienko.common.io_nio.reader_writer;

import org.junit.Before;
import org.junit.Test;

import java.io.*;


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
            outputStream.write("string");
            outputStream.write(new char[]{'c', 'h', 'a', 'r'});
        }

       /* try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            assertEquals(1, inputStream.read());
            assertEquals(255, inputStream.read());
        }*/
    }
}
