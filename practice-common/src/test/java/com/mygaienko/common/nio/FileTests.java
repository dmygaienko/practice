package com.mygaienko.common.nio;

import org.junit.Test;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.Arrays;

/**
 * Created by enda1n on 17.10.2016.
 */
public class FileTests {

    @Test
    public void testFile_ListRoots() throws Exception {
        System.out.println("listRoots:\n" + Arrays.toString(File.listRoots()));
    }

    @Test
    public void testFile_ListFiles() throws Exception {
        System.out.println("listFiles:\n" + Arrays.toString(new File("").getAbsoluteFile().listFiles()));
    }

    @Test
    public void testFile_ListFilesFiltered() throws Exception {
        System.out.println("testFile_ListFilesFiltered:\n" + Arrays.toString(new File("").getAbsoluteFile().listFiles(
                (file, name) -> "pom.xml".equals(name))));
    }

    @Test
    public void testFile_separators() throws Exception {
        System.out.println("pathSeparator:\n" + File.pathSeparator);
        System.out.println("pathSeparatorChar:\n" + File.pathSeparatorChar);
        System.out.println("separator:\n" + File.separator);
        System.out.println("separatorChar:\n" + File.separatorChar);
    }

    @Test
    public void testFile_spaces() throws Exception {
        for (File file : File.listRoots()) {
            System.out.println("path:" + file.getCanonicalPath());
            System.out.println("getFreeSpace:" + file.getFreeSpace());
            System.out.println("getUsableSpace:" + file.getUsableSpace());
            System.out.println("getTotalSpace:" + file.getTotalSpace() + "\n");
        }
    }

    @Test
    public void testRandomAccessFile() throws Exception {
        RandomAccessFile rws = new RandomAccessFile("test.dat", "rws");
        rws.writeChar('c');
    }
}
