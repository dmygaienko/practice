package com.mygaienko.common.stream;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by dmygaenko on 28/01/2016.
 */
public class StreamTest {

    private List<String> strings;

    private Stream<String> stringStream;

    @Before
    public void init() {
        strings = new ArrayList<String>();
        strings.add("string 1");
        strings.add("string 2");
        strings.add("string 3");
        strings.add("string 4");
        strings.add("string 5");
        strings.add("string 6");

        stringStream = strings.stream();
    }

    @Test
    public void testForEach() {
        stringStream.forEach(x -> System.out.println(x));
    }

    @Test
    public void testMap() {
        Stream<String> mappedStream = stringStream.map(x -> x + 1);

        System.out.println(Arrays.toString(mappedStream.toArray()));
    }

    @Test
    public void testFilter() {
        Stream<String> mappedStream = stringStream.filter(x -> x.matches("string [1|2|3|4|5]"));

        System.out.println(Arrays.toString(mappedStream.toArray()));
    }

    @Test
    public void testMatching() {
        "string 6".matches("string [1|2|3|4|5]");

    }

    @Test
    public void testDistinct() {
        strings.add("string 6");
        strings.add("string 6");

        stringStream = strings.stream();

        System.out.println(Arrays.toString(stringStream.distinct().toArray()));
    }

}
