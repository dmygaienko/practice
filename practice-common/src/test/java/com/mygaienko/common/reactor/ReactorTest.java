package com.mygaienko.common.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoProcessor;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.function.Function;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasEntry;
import static org.junit.Assert.assertThat;

public class ReactorTest {

    @Test
    public void testFlux() {
        Flux.just("1", "2", "3")
                .filter(item -> item.equals("2"))
                .subscribe(item -> System.out.println("item is " + item))
        ;
    }

    @Test
    public void testMono() {
        Mono.just("1")
                .filter(item -> !item.equals("2"))
                .subscribe(item -> System.out.println("item is " + item))
        ;
    }

    @Test
    public void testMonoConcatWith() {
        Mono.just("1").concatWith(Mono.just("2"))
                .subscribe(item -> System.out.println("item is " + item))
        ;
    }

    @Test
    public void testCollectListAndBlock() {
        List<String> list = Flux.just("1", "2")
                .collectList()
                .block()
                ;

        assertThat(list, contains("1", "2"));
    }

    @Test
    public void testCollectMap() {
        Map<String, String> map = Flux.just("1", "2")
                .collectMap(Function.identity(), Function.identity())
                .block()
                ;

        assertThat(map, hasEntry("1", "1"));
        assertThat(map, hasEntry("2", "2"));
    }

    @Test
    public void testDoOnNext() {
        Flux.just("1", "2", "3", "4")
                .doOnNext(item -> System.out.println("some action with " + item))
                .map(item -> item + "_mapped")
                .take(2)
//                .doFinally(item -> System.out.println("Finally with item " + item))
                .doOnNext(item -> {
                    if (item.equals("2_mapped")) {
                        throw new RuntimeException("Test runtime exc");
                    }
                })
                .subscribe(
                        item -> System.out.println("subscribe " + item),
                        error -> System.out.println(error),
                        () -> System.out.println("On complete")
                );


        ;
    }

    @Test
    public void testZipWith() {
        Flux.just("One", "Two", "Three", "Four")
                .zipWith(Flux.range(0, 4))
                .subscribe(tuple -> System.out.println(tuple))
        ;
    }

    @Test
    public void testZipWithCombinator() {
        Flux.just("One", "Two", "Three", "Four", "Five", "Six")
                .zipWith(Flux.range(0, 6), (s, i) -> i + ". " + s)
//                .delayElementsMillis(1)
                .distinct()
                .sort()
                .buffer(2)
                .take(2)
                .subscribe(combination -> System.out.println(combination))
        ;
    }

    @Test
    public void testFluxOnAnotherThread() {
        List<String> lists = Flux.range(0, 100000)
                .publishOn(Schedulers.fromExecutorService(Executors.newFixedThreadPool(2)))
                .filter(item -> !item.equals("2"))
                .map(item -> "item executed on " + Thread.currentThread())
//                .doOnNext(System.out::println)
                .distinct()
                .collectList()
                .block();

        System.out.println(lists);
    }

    @Test
    public void testParallel() {
        Flux.range(1, 10)
                .parallel(2)
                .runOn(Schedulers.parallel())
                .subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));
    }

    @Test
    //not working
    public void test() {
        Map<Integer, List<Integer>> grouped = Flux.range(1, 10)
                .groupBy(item -> item % 3)
                .doOnNext(item -> System.out.println(item))
                .collectMap(group -> group.key(), group -> group.collectList().subscribe().peek())

                .block();

        System.out.println(grouped);
    }


}
