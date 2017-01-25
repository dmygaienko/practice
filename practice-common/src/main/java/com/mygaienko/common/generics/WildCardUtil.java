package com.mygaienko.common.generics;

import java.util.Collection;

/**
 * Created by dmygaenko on 25/01/2017.
 */
public class WildCardUtil {

    public static void consumerSuper(Collection<? super Number> col) {
        col.add(new Integer("1"));
    }

    public static void producerExtends(Collection<? extends Number> col) {
        for (Number number : col) {
            Integer int1 = (Integer) number;
        }
    }

    public static void numberConsumer(Collection<Number> col) {
        col.add(new Integer("1"));
    }
}
