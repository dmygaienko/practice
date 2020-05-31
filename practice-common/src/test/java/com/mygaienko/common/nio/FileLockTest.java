package com.mygaienko.common.nio;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * Created by enda1n on 22.10.2016.
 */
public class FileLockTest {

    final static int MAX_QUERIES = 150000;
    final static int MAX_UPDATES = 150000;
    final static int REC_LENGTH = 16;

    static ByteBuffer buffer = ByteBuffer.allocate(REC_LENGTH);
    static IntBuffer intBuffer = buffer.asIntBuffer();

    @Test
    public void test() throws IOException {

        try (RandomAccessFile raf = new RandomAccessFile("temp", "rw" /*(writer) ? "rw" : "r"*/);
             FileChannel fc = raf.getChannel()) {
            update(fc);
            query(fc);
        }
    }

    static void query(FileChannel fc) throws IOException {
        for (int i = 0; i < MAX_QUERIES; i++) {
            System.out.println("acquiring shared lock");
            FileLock lock = fc.lock(0, REC_LENGTH, true);
            try {
                buffer.clear();
                fc.read(buffer, 0);
                int a = intBuffer.get(0);
                int b = intBuffer.get(1);
                int c = intBuffer.get(2);
                int d = intBuffer.get(3);
                System.out.println("Reading: " + a + " " +
                        b + " " +
                        c + " " +
                        d);
                if (a * 2 != b || a * 3 != c || a * 4 != d) {
                    System.out.println("error");
                    return;
                }
            } finally {
                lock.release();
            }
        }
    }

    static void update(FileChannel fc) throws IOException {
        for (int i = 0; i < MAX_UPDATES; i++) {
            System.out.println("acquiring exclusive lock");
            FileLock lock = fc.lock(0, REC_LENGTH, false);
            try {
                intBuffer.clear();
                int counter = 1;
                int a = counter;
                int b = counter * 2;
                int c = counter * 3;
                int d = counter * 4;
                System.out.println("Writing: " + a + " " +
                        b + " " +
                        c + " " +
                        d);
                intBuffer.put(a);
                intBuffer.put(b);
                intBuffer.put(c);
                intBuffer.put(d);
                counter++;
                buffer.clear();
                fc.write(buffer, 0);
            } finally {
                lock.release();
            }
        }
    }
}
