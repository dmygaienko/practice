package com.mygaienko.common.collection;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dmygaenko on 09/11/2016.
 */
public class Range {
    private static final Pattern pattern =  Pattern.compile("(\\d+)-(\\d+)");

    public static Set<Integer> getIndexesByRange(String range) {
        if (StringUtils.isEmpty(range)) return Collections.emptySet();

        Set<Integer> indexes = new HashSet<>();

        String[] splitted = range.split(",");
        for (String s : splitted) {
            String trimmed = s.trim();

            if (StringUtils.isNumeric(trimmed)) {
                parseAsNumeric(trimmed, indexes);

            } else {
                parseAsRange(trimmed, indexes);
            }
        }

        return indexes;
    }

    private static void parseAsNumeric(String s, Set<Integer> indexes) {
        indexes.add(Integer.valueOf(s));
    }

    private static void parseAsRange(String trimmed, Set<Integer> indexes) {
        Matcher matcher = pattern.matcher(trimmed);
        if (matcher.find()) {
            Integer from = Integer.valueOf(matcher.toMatchResult().group(1));
            Integer to = Integer.valueOf(matcher.toMatchResult().group(2));

            if (from > to) {
                throw new IllegalStateException("from should be less that to");
            }

            for (int i = from; i < to; i++) {
                indexes.add(i);
            }
        }
    }
}
