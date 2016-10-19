package com.mygaienko.common.nio;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * Created by enda1n on 20.10.2016.
 */
public class ByteBufferTest {
    @Test
    public void test() throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(7);

        System.out.println("Capacity = " + buffer.capacity());
        System.out.println("Limit = " + buffer.limit());
        System.out.println("Position = " + buffer.position());
        System.out.println("Remaining = " + buffer.remaining());

        buffer.put((byte) 10).put((byte) 20).put((byte) 30);
        System.out.println("Capacity = " + buffer.capacity());
        System.out.println("Limit = " + buffer.limit());
        System.out.println("Position = " + buffer.position());
        System.out.println("Remaining = " + buffer.remaining());

        for (int i = 0; i < buffer.position(); i++)
            System.out.println(buffer.get(i));

    }

    @Test
    public void testFlip() throws Exception {
        String[] poem = {
                        "Roses are red",
                        "Violets are blue",
                        "Sugar is sweet",
                        "And so are you."
                };
        CharBuffer buffer = CharBuffer.allocate(50);
        for (int i = 0; i < poem.length; i++) {

            for (int j = 0; j < poem[i].length(); j++) {
                buffer.put(poem[i].charAt(j));
            }

            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print(buffer.get());
            }

            buffer.clear();
            System.out.println();
        }
    }

    @Test
    public void testMarkAndReset() throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(7);
        buffer.put((byte) 10).put((byte) 20).put((byte) 30).put((byte) 40);
        buffer.limit(4);
        buffer.position(1).mark().position(3);
        System.out.println(buffer.get());
        System.out.println();
        buffer.reset();
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }
}
