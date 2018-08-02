package com.mygaienko.common.guava;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Range;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    public void test() {
        assertEquals(Long.valueOf(1L), MoreObjects.firstNonNull(null, 1L));
    }

}
