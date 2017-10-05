package com.mygaienko.common.concurrency;

import org.junit.Test;

/**
 * Created by enda1n on 05.10.2017.
 */
public class ThreadInterruptionTest {

    @Test
    public void test() throws Exception {
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
}
