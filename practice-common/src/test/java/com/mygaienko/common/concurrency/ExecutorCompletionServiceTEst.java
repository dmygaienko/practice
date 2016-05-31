package com.mygaienko.common.concurrency;

import org.junit.Test;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by enda1n on 26.05.2016.
 */
public class ExecutorCompletionServiceTest {

    private ExecutorService executor = Executors.newFixedThreadPool(5);
    private CompletionService completionService = new ExecutorCompletionService(executor);

    @Test
    public void testSubmitTake() throws Exception {
        completionService.submit(() -> "result1");
        completionService.submit(() -> doProcessing(), "result2");

        System.out.println("take: " + completionService.take().get());
        System.out.println("take: " + completionService.take().get());
    }

    @Test
    public void testSubmitPoll() throws Exception {
        completionService.submit(() -> "result1");
        completionService.submit(() -> doProcessing(), "result2");

        Future future1 = completionService.poll();
        System.out.println("poll: " + (future1 == null ? "null" : future1.get()));
        Future future2 = completionService.poll();
        System.out.println("poll: " + (future2 == null ? "null" : future2.get()));
    }

    @Test
    public void testSubmitWithTakeTasks() throws Exception {
        completionService.submit(() -> "result1");
        completionService.submit(() -> doProcessing(), "result2");
        completionService.submit(() -> doProcessing(), "result3");

        for (int i = 0; i < 3; i++) {
            executor.submit(getTakeTask(i));
        }

        executor.shutdown();
        executor.awaitTermination(60, TimeUnit.SECONDS);
        System.out.println("main");
    }

    private Runnable getTakeTask(int i) {
        return () -> {
            try {
                System.out.println("take from: " + i + " is " + completionService.take().get() );
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        };
    }

    private void doProcessing() {
        System.out.println("doProcessing");
    }
}
