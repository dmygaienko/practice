package com.mygaienko.common.nio;

import org.junit.Test;

import java.io.File;
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
    public void testFile_separators() throws Exception {
        System.out.println("pathSeparator:\n" + File.pathSeparator);
        System.out.println("pathSeparatorChar:\n" + File.pathSeparatorChar);
        System.out.println("separator:\n" + File.separator);
        System.out.println("separatorChar:\n" + File.separatorChar);
    }
}
