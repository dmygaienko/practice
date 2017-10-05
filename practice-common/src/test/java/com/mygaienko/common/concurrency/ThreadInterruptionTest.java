package com.mygaienko.common.concurrency;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by enda1n on 05.10.2017.
 */
public class ThreadInterruptionTest {

    @Test
    public void testInterruptionDuringSleep() throws Exception {
        Thread thread = new Thread(getRunnable());
        thread.start();

        Thread.sleep(1000);
        thread.interrupt();
        thread.join();
    }

    private Runnable getRunnable() {
        return () -> {
            while (true) {
                System.out.println("running");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("interrupted");
                    break;
                }
            }
        };
    }

    @Test
    public void testInterruptionDuringWait() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();

        Thread thread = new Thread(getRunnable(lock));
        thread.start();

       // Thread.sleep(1000);
        thread.interrupt();
        System.out.println("interrupted");
        thread.join();

        lock.unlock();
    }

    private Runnable getRunnable(ReentrantLock lock) {
        return () -> {
            System.out.println("waiting for lock");
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                System.out.println("interrupted");
                return;
            }
            System.out.println("lock accepted");
            lock.unlock();
        };
    }

}
