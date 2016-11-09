package com.mygaienko.common.io.stream;

import org.junit.Test;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by dmygaenko on 18/10/2016.
 */
public class PipedOutputInputStreamTest {

    private Semaphore semaphore = new Semaphore(1);

    @Test
    public void testReadWrite() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        PipedOutputStream outputStream = new PipedOutputStream();
        try (PipedInputStream inputStream = new PipedInputStream(outputStream)) {

            executorService.execute(getWriteCommand(outputStream));
            executorService.execute(getReadCommand(inputStream));

            executorService.awaitTermination(10, TimeUnit.SECONDS);
        }
    }

    private Runnable getReadCommand(PipedInputStream inputStream) {
        return () -> {
            while (!semaphore.tryAcquire()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            int b;
            try {
                while ((b = inputStream.read()) != -1) {
                    System.out.println(b);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            semaphore.release();
        };
    }

    private Runnable getWriteCommand(PipedOutputStream outputStream) {
        return () -> {
            while (!semaphore.tryAcquire()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            IntStream.range(1, 1000)
                    .forEach((i) -> {
                        try {
                            outputStream.write(i);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            semaphore.release();
        };
    }
}
