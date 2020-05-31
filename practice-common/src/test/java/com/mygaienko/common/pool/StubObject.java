package com.mygaienko.common.pool;

/**
 * Created by enda1n on 31.08.2017.
 */
class StubObject {
    private long number;

    public StubObject() {
        this.number = System.currentTimeMillis();
    }

    public long getNumber() {
        return number;
    }
}
