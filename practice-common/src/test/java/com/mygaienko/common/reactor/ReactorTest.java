package com.mygaienko.common.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;

public class ReactorTest {

    @Test
    public void test() {
        Flux.just("1", "2", "3")
                .filter(item -> item.equals("2"))
                .subscribe(item -> System.out.println("item is " + item))
        ;
    }
}
