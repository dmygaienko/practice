package com.mygaienko.common.collection.commons;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BidiMapTest {

    @Test
    public void test() {
        BidiMap<String, Long> bidiMap = new DualHashBidiMap();
        bidiMap.put("1", 1L);
        bidiMap.put("2", 2L);
        bidiMap.put("3", 3L);
        assertThat(bidiMap.getKey(1L), is("1"));

        BidiMap<Long, String> inverted = bidiMap.inverseBidiMap();
        assertThat(inverted.get(3L), is("3"));
    }
}
