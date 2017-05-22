package com.mygaienko.common.rxjava;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.TestScheduler;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import static com.mygaienko.common.util.TestUtils.getArrayList;
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
    public void testObserveNewElements() throws Exception {
        Observable
                .interval(1, TimeUnit.SECONDS)
                .skip(1)
                .take(5)
                //.flatMap(i -> rxChildrenOf("parent"))
                .flatMap(i -> Observable.fromIterable(getArrayList(0, i.intValue())))
                .distinct()
                .blockingSubscribe(str -> System.out.println(str));
    }

    @Test
    public void testBufferedObservable() throws Exception {
        Observable
                .interval(100, TimeUnit.MILLISECONDS)
                .buffer(10)
                .distinct()
                .blockingSubscribe(str -> System.out.println(str));
    }

    private Observable<String> rxChildrenOf(String parent) {
        return Observable
                .fromArray(new File(parent).listFiles())
                .map(File::getName);
    }

    @Test
    public void testSchedulers() throws Exception {
        final TestScheduler testScheduler = new TestScheduler();

        slowService()
                .timeout(1, TimeUnit.SECONDS, testScheduler)
                .retry(3)
                .doOnError(ex -> System.out.println(ex))
                .onErrorReturn(ex -> BigDecimal.ONE)
                .blockingSubscribe(str -> System.out.println(str));

        testScheduler.advanceTimeBy(5, TimeUnit.SECONDS);
    }

    private Observable<BigDecimal> slowService() {
        return Observable
                .interval(1, TimeUnit.SECONDS)
                .just(BigDecimal.ONE);

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
