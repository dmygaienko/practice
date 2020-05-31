package com.mygaienko.common.guava;

import com.google.common.base.MoreObjects;
import com.google.common.base.Stopwatch;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Range;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class GuavaTest {

    @Test
    public void testRange() {
        Range<Double> closed = Range.closed(1.0d, 10.0d);
        assertTrue(closed.contains(1.0d));

        Range<Double> open = Range.openClosed(5.0d, 6.0d);
        assertFalse(open.contains(5.0d));

        assertTrue(closed.isConnected(open));
        assertTrue(closed.encloses(open));
        assertEquals(open, closed.intersection(open));
    }

    @Test
    public void testStopWatch() throws InterruptedException {
        Stopwatch started = Stopwatch.createStarted();
        Thread.sleep(1000);
        System.out.println(started.stop().elapsed(TimeUnit.SECONDS));
    }

    @Test
    public void testFirstNonNull() {
        assertEquals(Long.valueOf(1L), MoreObjects.firstNonNull(null, 1L));
    }

    @Test
    public void testCache() {
        CacheLoader<String, String> loader = new CacheLoader<String, String>() {
            @Override
            public String load(String key) {
                return key.toUpperCase();
            }
        };

        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(3)
//                .weigher((key, value) -> value.toString().length())
                .expireAfterAccess(2, TimeUnit.MINUTES)
                .refreshAfterWrite(2, TimeUnit.MINUTES)
                .build(loader);

        cache.getUnchecked("first");
        cache.getUnchecked("second");
        cache.getUnchecked("third");
        cache.getUnchecked("forth");

        assertEquals(3, cache.size());
        assertNull(cache.getIfPresent("first"));
        assertEquals("FORTH", cache.getIfPresent("forth"));
    }

}
