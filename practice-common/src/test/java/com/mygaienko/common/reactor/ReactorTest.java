package com.mygaienko.common.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
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
                .delayElementsMillis(1)
                .distinct()
                .sort()
                .buffer(2)
                .take(2)
                .subscribe(combination -> System.out.println(combination))
        ;
    }

}
