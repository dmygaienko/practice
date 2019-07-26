package com.mygaienko.common.concurrency.userexperience;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUserServiceTest {

    @Mock
    private ConfigProvider configProvider;

    @Mock
    private Subscriber subscriber;

    @Mock
    private Subscriber subscriberTwo;

    private List<Subscriber> subscribers = new ArrayList<>();

    private UserService userService;

    private ExecutorService executorService = Executors.newFixedThreadPool(4);

    @Before
    public void setUp() {
        when(configProvider.getConfigs()).thenReturn(Collections.unmodifiableMap(
                Stream.of(
                        new SimpleEntry<>(0L, 0L),
                        new SimpleEntry<>(1L, 100L),
                        new SimpleEntry<>(2L, 200L),
                        new SimpleEntry<>(3L, 300L),
                        new SimpleEntry<>(4L, 400L),
                        new SimpleEntry<>(5L, 500L),
                        new SimpleEntry<>(6L, 1000L),
                        new SimpleEntry<>(7L, 2000L),
                        new SimpleEntry<>(8L, 3000L),
                        new SimpleEntry<>(9L, 4000L),
                        new SimpleEntry<>(10L, 5000L),
                        new SimpleEntry<>(11L, 10000L),
                        new SimpleEntry<>(12L, 20000L))
                        .collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue))
        ));

        userService = new DefaultUserService(configProvider);
        addSubscriber(subscriber);
        addSubscriber(subscriberTwo);
    }

    private void addSubscriber(Subscriber subscriber) {
        userService.addSubscriber(subscriber);
        subscribers.add(subscriber);
    }

    @Test
    public void testSimpleAdd() {
        userService.addExperience(1, 300);
        assertEquals(300, userService.getExperience(1));
        assertEquals(3, userService.getLevel(1));

        verifySubscribersNotified(1L,1L, 3L);
    }

    private void verifySubscribersNotified(Long userId, Long levelFrom, Long levelTo) {
        verifySubscribersNotified(userId, userId, levelFrom, levelTo);
    }

    private void verifySubscribersNotified(Long userIdFrom, Long userIdTo, Long levelFrom, Long levelTo) {
        LongStream.rangeClosed(userIdFrom, userIdTo)
                .forEach(userId ->
                        LongStream.rangeClosed(levelFrom, levelTo)
                                .forEach(levelRaised ->
                                        subscribers
                                                .forEach(subscriber -> verify(subscriber).notify(userId, levelRaised))
                                ));
    }

    @Test
    public void test0lvlLowestExp0() {
        userService.addExperience(1, 0);
        assertEquals(0, userService.getExperience(1));
        assertEquals(0, userService.getLevel(1));
    }

    @Test
    public void test0lvlLowestExp1() {
        userService.addExperience(1, 1);
        assertEquals(1, userService.getExperience(1));
        assertEquals(0, userService.getLevel(1));
    }

    @Test
    public void test0lvlPeakExp() {
        userService.addExperience(1, 99);
        assertEquals(99, userService.getExperience(1));
        assertEquals(0, userService.getLevel(1));
    }

    public void test1lvlLowestExp0() {
        userService.addExperience(1, 100);
        assertEquals(100, userService.getExperience(1));
        assertEquals(1, userService.getLevel(1));

        verifySubscribersNotified(1L,1L, 1L);
    }

    @Test
    public void test1lvlLowestExp1() {
        userService.addExperience(1, 101);
        assertEquals(101, userService.getExperience(1));
        assertEquals(1, userService.getLevel(1));

        verifySubscribersNotified(1L,1L, 1L);
    }

    @Test
    public void test1lvlPeakExp() {
        userService.addExperience(1, 199);
        assertEquals(199, userService.getExperience(1));
        assertEquals(1, userService.getLevel(1));

        verifySubscribersNotified(1L,1L, 1L);
    }

    @Test
    public void testThreeAdds() {
        userService.addExperience(1, 300);
        userService.addExperience(1, 1000);
        userService.addExperience(1, 2000);

        assertEquals(3300, userService.getExperience(1));
        assertEquals(8, userService.getLevel(1));

        verifySubscribersNotified(1L,1L, 8L);
    }

    @Test
    public void testThreeAddsInParallel() throws ExecutionException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        CompletableFuture.allOf(
                CompletableFuture.runAsync(inParallel(latch, () -> userService.addExperience(1, 300)), executorService),
                CompletableFuture.runAsync(inParallel(latch, () -> userService.addExperience(1, 1000)), executorService),
                CompletableFuture.runAsync(inParallel(latch, () -> userService.addExperience(1, 2000)), executorService)).
                get();

        assertEquals(3300, userService.getExperience(1));
        assertEquals(8, userService.getLevel(1));

        verifySubscribersNotified(1L,1L, 8L);
    }

    private Runnable inParallel(CountDownLatch latch, Runnable runnable) {
        return () -> {
            latch.countDown();
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runnable.run();
        };
    }

    @Test
    public void test10000ExpInParallel() throws ExecutionException, InterruptedException {
        CompletableFuture[] futures = new CompletableFuture[10000];
        for (int i = 0; i < 10000; i++) {
            futures[i] = (CompletableFuture.runAsync(() -> userService.addExperience(1, 2), executorService));
        }
        CompletableFuture.allOf(futures).get();

        assertEquals(20000, userService.getExperience(1));
        assertEquals(12, userService.getLevel(1));

        verifySubscribersNotified(1L,1L, 12L);
    }

    @Test
    public void test10000ExpInParallelToTwoUsers() throws ExecutionException, InterruptedException {
        CompletableFuture[] futures = new CompletableFuture[10000];
        for (int i = 0; i < 10000; i++) {
            int finalI = i;
            futures[i] = (CompletableFuture.runAsync(() -> userService.addExperience(finalI % 2 + 1, 2), executorService));
        }
        CompletableFuture.allOf(futures).get();

        assertEquals(10000, userService.getExperience(1));
        assertEquals(11, userService.getLevel(1));

        assertEquals(10000, userService.getExperience(2));
        assertEquals(11, userService.getLevel(2));

        verifySubscribersNotified(1L, 2L, 1L, 11L);
    }

}