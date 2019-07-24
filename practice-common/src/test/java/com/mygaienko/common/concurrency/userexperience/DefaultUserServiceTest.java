package com.mygaienko.common.concurrency.userexperience;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUserServiceTest {

    @Mock
    private ConfigProvider configProvider;

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
    }

    @Test
    public void test() {
        userService.addExperience(1, 300);
        assertEquals(300, userService.getExperience(1));
        assertEquals(3, userService.getLevel(1));
    }

    @Test
    public void testThreeAdds() {
        userService.addExperience(1, 300);
        userService.addExperience(1, 1000);
        userService.addExperience(1, 2000);

        assertEquals(3300, userService.getExperience(1));
        assertEquals(8, userService.getLevel(1));
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
}