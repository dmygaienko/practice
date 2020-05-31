package com.mygaienko.common.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;

/**
 * Created by enda1n on 22.10.2016.
 */
public class ChannelTest {


    @Test
    public void test() throws Exception {

        try (FileInputStream fileInputStream = new FileInputStream(getFile("1mb.txt"));
            ) {

            ScatteringByteChannel inputChannel = fileInputStream.getChannel();
            ByteBuffer buffer1 = ByteBuffer.allocateDirect(20000);
            ByteBuffer buffer2 = ByteBuffer.allocateDirect(20000);

            ByteBuffer[] byteBuffers = {buffer1, buffer2};

            inputChannel.read(byteBuffers);

            for (ByteBuffer byteBuffer : byteBuffers) {
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    System.out.println(byteBuffer.get());
                }
            }

            for (ByteBuffer byteBuffer : byteBuffers) {
                byteBuffer.rewind();
            }

            //getFile("1mb_out.txt")
            FileOutputStream fileOutputStream = new FileOutputStream("y.dat");
            GatheringByteChannel outputChannel = fileOutputStream.getChannel();
            outputChannel.write(new ByteBuffer[] {buffer2, buffer1});
        }
    }

    private String getFile(String name) {
        return getClass().getResource(name).getFile();
    }
}
