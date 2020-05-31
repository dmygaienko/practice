package com.mygaienko.common.fastutil;

import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.longs.Long2ReferenceArrayMap;
import it.unimi.dsi.fastutil.longs.LongOpenHashBigSet;
import org.junit.Test;

public class FastUtilTest {

    @Test
    public void testDoubleArrayList() {
        DoubleArrayList list = new DoubleArrayList();
        list.add(1.2);
        list.add(1.3);
        list.add(1.4);
        list.add(1.5);
        System.out.println(list);
    }

    @Test
    public void testLong2ReferenceArrayMap() {
        Long2ReferenceArrayMap<Object> map = new Long2ReferenceArrayMap<>();
        map.put(1, new Object());
        map.put(2, new Object());
        map.put(3, new Object());
        System.out.println(map);
    }

    @Test
    public void testLongOpenHashBigSet() {
        LongOpenHashBigSet set = new LongOpenHashBigSet();
        set.add(1);
        set.add(1);
        set.add(2);
        set.add(3);
        System.out.println(set);
    }
}

