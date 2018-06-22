package com.mygaienko.patterns.behavioral.observer;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by enda1n on 08.11.2017.
 */
public class Context {

    private Set<String> values = new HashSet<>();

    public void setValue(String value) {
        values.add(value);
    }

    public boolean contains(String value) {
        return values.contains(value);
    }
}
