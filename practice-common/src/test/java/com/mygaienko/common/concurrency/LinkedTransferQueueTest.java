package com.mygaienko.common.concurrency;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.*;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

/**
 * Created by dmygaenko on 03/10/2017.
 */
public class LinkedTransferQueueTest {

    private ExecutorService executorService;
    private LinkedTransferQueue<String> linkedTransferQueue;


    @Before
    public void setUp() throws Exception {
        executorService = Executors.newFixedThreadPool(2);
        linkedTransferQueue = new LinkedTransferQueue<>();
    }

    @Test
    public void testOnePublishTwoSubscribers() throws InterruptedException {
        ConcurrentLinkedQueue<String> resultQueue = new
                ConcurrentLinkedQueue<>();

        executorService.execute(getPublisher("1", "2", "3"));
        executorService.execute(getSubscriber(3, resultQueue));
        executorService.shutdown();
        executorService.awaitTermination(1000, TimeUnit.SECONDS);

        assertThat(resultQueue, containsInAnyOrder("1", "2", "3"));
    }

    private Runnable getSubscriber(int i,
                                   ConcurrentLinkedQueue<String> resultQueue) {
        return () -> {
            for (int j = 0; j < i; j++) {
                try {
                    resultQueue.add(linkedTransferQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private Runnable getPublisher(String... strings) {
        return () -> {
            for (String string : strings) {
                try {
                    linkedTransferQueue.transfer(string);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}