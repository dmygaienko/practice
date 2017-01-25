package com.mygaienko.common.generics;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

/**
 * Created by dmygaenko on 25/01/2017.
 */
public class WildCardUtilTest {


    @Test
    public void testConsumer() throws Exception {
        ArrayList<Number> col = new ArrayList<>();
        WildCardUtil.numberConsumer(col);

        assertTrue(col.contains(new Integer("1")));
    }

    @Test
    public void testConsumerSuper() throws Exception {
        WildCardUtil.consumerSuper(new ArrayList<>());
    }

    @Test
    public void testProducerExtends() throws Exception {
        WildCardUtil.producerExtends(new ArrayList<Integer>());
    }
}
