package com.mygaienko.common.concurrency;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by enda1n on 31.08.2016.
 */
public class ReentrantReadWriteLockTest {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    private volatile boolean reading = true;

    private void read() {
        System.out.println("writer try to lock");
        lock.writeLock().lock();
        System.out.println("writer got lock");
        lock.writeLock().unlock();
        System.out.println("writer unlocked");
    }

    @Test
    public void testWithThreads() throws InterruptedException {
        startReader("1");
        startReader("2");
        startReader("3");
        startWriter();
        startReader("4");
        reading = false;
        Thread.sleep(3000);

    }

    private void startWriter() {
        new Thread() {
            @Override
            public void run() {
                read();
            }
        }.start();
    }

    private void startReader(String id) {
        new Thread() {
            @Override
            public void run() {
                read(id);
            }
        }.start();
    }

    private void read(String id) {
        System.out.println("reader " + id + " try to lock");
        lock.readLock().lock();
        System.out.println("reader " + id + " got lock");
        while (reading) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lock.readLock().unlock();
        System.out.println("reader " + id + " unlocked");
    }
}
