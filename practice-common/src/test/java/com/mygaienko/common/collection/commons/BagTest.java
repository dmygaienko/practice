package com.mygaienko.common.collection.commons;

import org.apache.commons.collections.Bag;
import org.apache.commons.collections.bag.HashBag;
import org.apache.commons.collections.bag.TreeBag;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BagTest {

    @Test
    public void testHashBag() {
        Bag bag = new HashBag();
        bag.add("1", 3);
        bag.add(2, 1);
        bag.add(3, 4);
        bag.add(5, 4);

        assertThat(bag.getCount("1"), is(3));
        assertThat(bag.getCount(2), is(1));
    }

    @Test
    public void testTreeBag() {
        TreeBag bag = new TreeBag();
        bag.add("1", 3);
        bag.add("2", 1);
        bag.add("3", 4);
        bag.add("5", 4);

        assertThat(bag.getCount("1"), is(3));
        assertThat(bag.getCount("2"), is(1));
    }

}
