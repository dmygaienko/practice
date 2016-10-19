package com.mygaienko.common.io.stream;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by dmygaenko on 18/10/2016.
 */
public class FileInputOutputStreamTest {

    @Test
    public void testStream() throws Exception {
        File file = new File("FileInputOutputStreamTest.dat");
        file.delete();
        file.createNewFile();
        file.deleteOnExit();

        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(1);
            fileOutputStream.write(222);
            fileOutputStream.flush();
        }

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            assertEquals(1, fileInputStream.read());
            assertEquals(2, fileInputStream.read());
        }
    }
}
