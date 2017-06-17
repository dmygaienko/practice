package com.mygaienko.common;

import org.junit.Test;

/**
 * Created by enda1n on 17.06.2017.
 */
public class ByteTest {

    @Test
    public void testAnd() throws Exception {
        byte b1 = 0b0001;
        byte b2 = 0b1111;
        byte b3 = 0b11;

        System.out.println("b1 :" + Integer.toBinaryString(b1));
        System.out.println("b2 :" + Integer.toBinaryString(b2));
        System.out.println("-1 :" + Integer.toBinaryString(-1));
        System.out.println("-2 :" + Integer.toBinaryString(-2));
        System.out.println("-127 :" + Integer.toBinaryString(-127));
        System.out.println("1 :" + Integer.toBinaryString(1));

        System.out.println("b1 | b2:" + Integer.toBinaryString(b1 | b2));
        System.out.println("b1 & b2:" + Integer.toBinaryString(b1 & b2));
        System.out.println("b1 ^ b2:" + Integer.toBinaryString(b1 ^ b2));
        System.out.println("b1 ^ b3:" + Integer.toBinaryString(b1 ^ b3));
        System.out.println("b1 >> 2:" + Integer.toBinaryString(b2 >> 2));
        System.out.println("b1 >>> 2:" + Integer.toBinaryString(b2 >>> 2));
        System.out.println("b1 << b2:" + Integer.toBinaryString(b2 << 2));
    }
}
