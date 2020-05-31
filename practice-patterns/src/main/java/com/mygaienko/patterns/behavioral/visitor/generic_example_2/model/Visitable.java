package com.mygaienko.patterns.behavioral.visitor.generic_example_2.model;


import com.mygaienko.patterns.behavioral.visitor.generic_example_2.visitors.Visitor;

/**
 * Created by dmygaenko on 23/09/2016.
 */
public interface Visitable<T> {

    default void accept(Visitor<T> visitor) {
        visitor.visit((T) this);
    }
}
