package com.mygaienko.common.concurrency;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by dmygaenko on 23/06/2016.
 */
public class CopyOnWriteArrayListTest {

    private CopyOnWriteArrayList<String> copyOnWriteArrayList;
    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    private ReentrantLock lock = new ReentrantLock();

    private volatile boolean writed = false;

    @Before
    public void init() {
        copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        copyOnWriteArrayList.add("value1");
        copyOnWriteArrayList.add("value2");
    }

    @Test
    public void test() {
        iterate();
        addValue("value3");
        addValue("value4");
    }

    private void addValue(String value) {
        executor.execute(() -> copyOnWriteArrayList.add(value));
    }

    private void iterate() {
        executor.execute(() -> {
            for (String s : copyOnWriteArrayList) {
                assertNotEquals("value4", s);
            }
        });
    }

    @Test
    public void testWriteRead() throws InterruptedException {
        executor.execute(readableTask());
        executor.execute(writableTask());

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
    }

    private Runnable writableTask() {
        return () -> {
            System.out.println("writable lock 1");
            lock.lock();

            writed = true;
            copyOnWriteArrayList.add("value3");
            copyOnWriteArrayList.add("value4");

            System.out.println("writable unlock 1");
            lock.unlock();
        };
    }

    private Runnable readableTask() {
        return () -> {
            System.out.println("readable lock 1");
            lock.lock();

            Iterator<String> iterator = copyOnWriteArrayList.iterator();

            System.out.println("readable unlock 1");
            lock.unlock();

            while (!writed) {
                try {
                    System.out.println("sleeped");
                    Thread.sleep(100);
                } catch (Exception e) {
                }
            }

            System.out.println("readable lock 2");
            lock.lock();

            while (iterator.hasNext()) {
                System.out.println("iterator 1: " +  iterator.next());
            }

            for (String s : copyOnWriteArrayList) {
                System.out.println("iterator 2: " + s);
            }

            System.out.println("readable unlock 2");
            lock.unlock();
        };

    }

    @Test
    public void testReadTaskNotSeeWrittenValues() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        executor.execute(() -> safeTestRead(countDownLatch));

        executor.execute(() -> {
            copyOnWriteArrayList.add("new value xxx");
            countDownLatch.countDown();
        });

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
    }

    private void safeTestRead(CountDownLatch countDownLatch) {
        try {
            testRead(countDownLatch);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void testRead(CountDownLatch countDownLatch) throws InterruptedException {
        Iterator<String> iterator = copyOnWriteArrayList.iterator();

        int i = 0;
        while (iterator.hasNext()) {
            String value = iterator.next();

            if (i == 1) {
                countDownLatch.await();
            }

            assertNotEquals("new value xxx", value);
            System.out.println(value);
            i++;
        }
    }

}
