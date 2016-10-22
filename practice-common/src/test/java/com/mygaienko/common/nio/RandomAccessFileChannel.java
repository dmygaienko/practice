package com.mygaienko.common.nio;

import org.junit.Test;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by enda1n on 22.10.2016.
 */
public class RandomAccessFileChannel {

    @Test
    public void test() throws Exception {
        RandomAccessFile raf = new RandomAccessFile("temp", "rw");
        FileChannel fc = raf.getChannel();

        long pos;
        System.out.println("Position = " + (pos = fc.position()));
        System.out.println("size: " + fc.size());
        String msg = "This is a test message.";
        ByteBuffer buffer = ByteBuffer.allocateDirect(msg.length() * 2);
        buffer.asCharBuffer().put(msg);

        fc.write(buffer);
        fc.force(true);
        System.out.println("position: " + fc.position());
        System.out.println("size: " + fc.size());
        buffer.clear();
        fc.position(pos);
        fc.read(buffer);
        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.print(buffer.getChar());
        }

    }
}
