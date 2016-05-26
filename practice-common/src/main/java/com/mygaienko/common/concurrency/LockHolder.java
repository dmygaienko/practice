package com.mygaienko.common.concurrency;

import java.util.concurrent.Semaphore;

/**
 * Created by dmygaenko on 26/05/2016.
 */
public enum LockHolder {
    INSTANCE;

    public final Semaphore readLock = new Semaphore(2);

    public final Semaphore writeLock = new Semaphore(4);

    public void getReadLock() throws InterruptedException {
        readLock.acquire();
    }

    public void releaseReadLock() throws InterruptedException {
        readLock.release();
    }

    public void getWriteLock() throws InterruptedException {
        writeLock.acquire();
    }

    public void releaseWriteLock() throws InterruptedException {
        writeLock.release();
    }
}
