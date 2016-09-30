package com.mygaienko.common.memory;

import java.lang.instrument.Instrumentation;

/**
 * Created by dmygaenko on 30/09/2016.
 */
public class Agent {

    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation inst) {
        instrumentation = inst;
    }

    public static long getObjectSize(Object o) {
        return instrumentation.getObjectSize(o);
    }
}
