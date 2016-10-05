package com.mygaienko.common.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by dmygaenko on 05/10/2016.
 */
public class StackTest {

    private static final String tableNames = "<a/>\n" +
            "<b/>\n" +
            "<c/>\n" +
            "<d/>\n" +
            "<e/>\n";

    @Test
    public void testStack() throws Exception {
        Stack stack = new Stack();
        stack.addAll(Arrays.asList(tableNames.split("\n")));

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
