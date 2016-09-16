package com.mygaienko.common.concurrency;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dmygaenko on 16/09/2016.
 */
public class CountDownLatchAsCyclicBarrierTest {

    @Test
    public void test() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(4);

        for (int o = 0; o < 3; ++o) {

            CountDownLatch startSignal = new CountDownLatch(1);
            CountDownLatch doneSignal = new CountDownLatch(4);

            for (int i = 0; i < 4; ++i) // create and start threads
                service.submit(new Worker(startSignal, doneSignal));

            System.out.println("main thread start workers: " + o + " iteration");
            startSignal.countDown();      // let all threads proceed
            doneSignal.await();           // wait for all to finish
        }
    }

    class Worker implements Runnable {
        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;

        Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }

        public void run() {
            try {
                startSignal.await();
                doWork();
                doneSignal.countDown();
            } catch (InterruptedException ex) {
            } // return;
        }

        void doWork() {
            System.out.println("Thread " + Thread.currentThread().getId() + " worker do work");
        }
    }
}
