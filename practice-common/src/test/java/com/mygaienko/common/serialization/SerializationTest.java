package com.mygaienko.common.serialization;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by dmygaenko on 22/06/2016.
 */
public class SerializationTest {

    @Test
    public void testTestClass() throws Exception {
        testClass(new TestClass("test", 1));
    }

    @Test
    public void testTestReadWriteClass() throws Exception {
        testClass(new TestReadWriteClass("test", 1));
    }

    private void testClass(Object testClass) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

        objectOutputStream.writeObject(testClass);

        byte[] bytes = byteArrayOutputStream.toByteArray();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        assertEquals(testClass, objectInputStream.readObject());
    }

}

