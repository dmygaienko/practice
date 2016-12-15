package com.mygaienko.common.concurrency;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by enda1n on 15.12.2016.
 */
public class ExecutorServiceTest {

    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    @Test
    public void testExecutors() throws Exception {
        List<Callable<Integer>> callables = getCallables();

        List<Future<Integer>> futures = executor.invokeAll(callables);
        futures.forEach((future) -> {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void testExecutorCompletionService() throws Exception {
        List<Callable<Integer>> callables = getCallables();

        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(executor);
        callables.forEach((callable) -> completionService.submit(callable));

        for (int i = 0; i < callables.size(); i ++) {
            System.out.println(completionService.take().get());
        }

    }

    private List<Callable<Integer>> getCallables() {
        List<Callable<Integer>> callables = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            int finalI = i;
            callables.add(() -> {
                Thread.sleep(finalI * 300);
                return finalI;
            });
        }
        return callables;
    }
}
