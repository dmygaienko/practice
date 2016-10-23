package com.mygaienko.common.nio;

import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Paths;
import java.util.concurrent.Future;

/**
 * Created by enda1n on 24.10.2016.
 */
public class AsynchronousFileChannelTest {

    @Test
    public void test() throws Exception {
        AsynchronousFileChannel ch = AsynchronousFileChannel.open(Paths.get(getFile("1mb.txt")));
        ByteBuffer buf = ByteBuffer.allocate(1024);
        Future<Integer> result = ch.read(buf, 0);
        while (!result.isDone())
        {
            System.out.println("Sleeping...");
            Thread.sleep(500);
        }
        System.out.println("Finished = " + result.isDone());
        System.out.println("Bytes read = " + result.get());
        ch.close();
    }

    private URI getFile(String name) throws URISyntaxException {
        return getClass().getResource(name).toURI();
    }
}
