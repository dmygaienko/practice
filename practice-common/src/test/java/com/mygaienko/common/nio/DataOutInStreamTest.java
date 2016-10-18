package com.mygaienko.common.nio;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by enda1n on 18.10.2016.
 */
public class DataOutInStreamTest {

    private File file;

    @Before
    public void setUp() throws Exception {
        file = new File("DataOutInStreamTest.dat");
        file.createNewFile();
        file.deleteOnExit();
    }

    @Test
    public void test() throws Exception {
        try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file))) {
            outputStream.write(1);
            outputStream.writeByte(125);
            outputStream.writeChar('t');
            outputStream.writeBoolean(true);
            outputStream.writeLong(1000L);

        }

        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(file))) {
            assertEquals(1, inputStream.read());
            assertEquals(125, inputStream.readByte());
            assertEquals('t', inputStream.readChar());
            assertEquals(true, inputStream.readBoolean());
            assertEquals(1000L, inputStream.readLong());
        }
    }
}
