package com.mygaienko.common.concurrency;

import java.util.concurrent.CountDownLatch;

/**
 * Created by enda1n on 26.05.2016.
 */
public enum CountDownLatchHolder {
    INSTANCE;

    public final CountDownLatch latch = new CountDownLatch(5);
}
