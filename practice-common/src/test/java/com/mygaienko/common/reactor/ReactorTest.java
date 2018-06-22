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

}
