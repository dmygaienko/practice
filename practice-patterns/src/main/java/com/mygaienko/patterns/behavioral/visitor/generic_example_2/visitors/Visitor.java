package com.mygaienko.patterns.behavioral.visitor.generic_example_2.visitors;

/**
 * Created by dmygaenko on 23/09/2016.
 */
public interface Visitor<T> {

    void visit(T t);
}
