package com.mygaienko.common.nio;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by enda1n on 18.10.2016.
 */
public class ByteArrayOutInStreamTest {

    @Test
    public void test() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        outputStream.write(1);
        outputStream.write(2);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        assertEquals(1, inputStream.read());
        assertEquals(2, inputStream.read());
    }
}
