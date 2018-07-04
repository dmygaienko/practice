package com.mygaienko.common.collection;

import org.junit.Test;

import java.util.BitSet;

public class BitSetTest {

    @Test
    public void test() {
        BitSet set = new BitSet();
        set.set(0, false);
        set.set(1, 100000, true);

        System.out.println(set.get(0));
        System.out.println(set.get(99999));
    }
}
