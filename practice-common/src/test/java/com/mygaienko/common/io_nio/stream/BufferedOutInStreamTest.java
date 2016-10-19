package com.mygaienko.common.io_nio.stream;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by enda1n on 18.10.2016.
 */
public class BufferedOutInStreamTest {

    private File file;

    @Before
    public void setUp() throws Exception {
        file = new File("BufferedOutInStreamTest.dat");
        file.delete();
        file.createNewFile();
        file.deleteOnExit();
    }

    @Test
    public void test() throws Exception {
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(1);
            outputStream.write(255);
        }

        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            assertEquals(1, inputStream.read());
            assertEquals(255, inputStream.read());
        }
    }
}
