package com.mygaienko.common.rxjava;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

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
    public void testMerge() throws Exception {
        final Observable<String> observable1 = rxFetch("city1");
        final Observable<String> observable2 = rxFetch("city2").timeout(1000, TimeUnit.MILLISECONDS);
        final Observable<String> allObservable = observable1.mergeWith(observable2).subscribeOn(Schedulers.io());

        assertThat(allObservable.blockingFirst(), is("weather in city1"));
    }

    @Test
    public void testFetch() throws Exception {
        assertThat(rxFetch("city").blockingFirst(), is("weather in city"));
    }

    @Test
    public void testInterval() throws Exception {
        Observable
                .interval(1, TimeUnit.SECONDS)
                .take(5)
                .blockingSubscribe(System.out::println);
    }

    @Test(expected = RuntimeException.class)
    public void testFetchWithTimeout() throws Exception {
        final Observable<String> observable = rxFetch("city")
                .timeout(500, TimeUnit.MILLISECONDS);
        assertThat(observable.blockingFirst(), is("weather in city"));
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
