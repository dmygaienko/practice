package com.mygaienko.common.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Created by enda1n on 22.10.2016.
 */
public class TransferChannelTest {

    @Test
    public void test() throws Exception {
        try (FileInputStream fis = new FileInputStream(getFile("1mb.txt"));
             FileChannel inChannel = fis.getChannel();
             WritableByteChannel outChannel = Channels.newChannel(System.out)) {

            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (IOException ioe) {
            System.out.println("I/O error: " + ioe.getMessage());
        }
    }

    private String getFile(String name) {
        return getClass().getResource(name).getFile();
    }
}
