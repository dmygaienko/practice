package com.mygaienko.common.string;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by dmygaenko on 02/06/2016.
 */
public class StringUtilsTest {

    @Test
    public void test() throws Exception {
        System.out.println(StringUtils.difference("move 1", "mve 1"));
    }

    @Test
    public void test1() throws Exception {
        System.out.println("'" + StringUtils.difference("move 1", " mve 1 ") + "'");
    }

    @Test
    public void testSplit() throws Exception {
        System.out.println(Arrays.toString("........................................".split("")));
    }
}
