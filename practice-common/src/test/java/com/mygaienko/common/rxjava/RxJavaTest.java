package com.mygaienko.common.rxjava;

import io.reactivex.Observable;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by enda1n on 21.05.2017.
 */
public class RxJavaTest {

    @Test
    public void test() throws Exception {
        Observable<Integer> numbers = Observable.just(1, 2, 3);

        numbers.subscribe((number) -> System.out.println(number));
    }

    @Test
    public void testFetch() throws Exception {
        assertThat(rxFetch("city").blockingFirst(), is("weather in city"));
    }

    private Observable<String> rxFetch(String city) {
        return Observable.fromCallable(() -> fetchWeather(city));
    }

    private String fetchWeather(String city) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "weather in " + city;
    }

}
