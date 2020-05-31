package com.mygaienko.common.collection.commons;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.bag.TreeBag;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BagTest {

    @Test
    public void testHashBag() {
        testBag(new HashBag<>());
    }

    private void testBag(Bag<String> bag) {
        bag.add("1", 3);
        bag.add("2", 1);
        bag.add("3", 4);
        bag.add("5", 4);

        assertThat(bag.getCount("1"), is(3));
        assertThat(bag.getCount("2"), is(1));
    }

    @Test
    public void testTreeBag() {
        testBag(new TreeBag<>());
    }

}
