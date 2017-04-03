package com.mygaienko.common.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by dmygaenko on 03/04/2017.
 */
public class HashSetTest {

    @Test
    public void testClone() throws Exception {
        HashSet<String> strings = new HashSet<>(Arrays.asList("1", "2", "3"));

        HashSet<String> strings1 = (HashSet<String>) strings.clone();
        HashSet<String> strings2 = (HashSet<String>) strings.clone();

        strings.add("4");
        strings1.add("5");
        strings2.add("6");

        assertTrue(strings.contains("4"));
        assertFalse(strings.contains("5"));
        assertFalse(strings.contains("6"));

        assertTrue(strings1.contains("5"));
        assertFalse(strings1.contains("4"));
        assertFalse(strings1.contains("6"));

        assertTrue(strings2.contains("6"));
        assertFalse(strings2.contains("4"));
        assertFalse(strings2.contains("5"));
    }
}
