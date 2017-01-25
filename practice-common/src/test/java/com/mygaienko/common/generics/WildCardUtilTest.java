package com.mygaienko.common.generics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void testCovarianceContravariance() throws Exception {
        //List<Number> list = new ArrayList<Integer>(); //error covariance impossible
        //List<Integer> list = new ArrayList<Number>(); //error contravariance impossible

        List<? extends Number> producer = new ArrayList<Integer>(); //джокер символ
        for (Number number : producer) {
            System.out.println(number);
        }

        List<? super Integer> consumer = new ArrayList<Integer>();
        consumer.add(new Integer("1"));
    }
}
