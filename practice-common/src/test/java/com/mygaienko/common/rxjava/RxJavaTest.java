package com.mygaienko.common.rxjava;

import io.reactivex.Observable;
import org.junit.Test;

/**
 * Created by enda1n on 21.05.2017.
 */
public class RxJavaTest {

    @Test
    public void test() throws Exception {
        Observable<Integer> numbers = Observable.just(1, 2, 3);

        numbers.subscribe((number) -> System.out.println(number));
    }
}
