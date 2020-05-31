package com.mygaienko.common.io.path;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

/**
 * Created by dmygaenko on 12/10/2017.
 */
public class PathTest {

    @Test
    public void testPath() throws Exception {
        Path path = Paths.get("");
        Path absolutePath = path.toAbsolutePath();
        System.out.println(absolutePath);

        Path parent = absolutePath.getParent();
        Path root = absolutePath.getRoot();

        System.out.println("parent: " + parent);
        System.out.println("parent: " + root);

        assertTrue(parent.startsWith(root));


    }
}