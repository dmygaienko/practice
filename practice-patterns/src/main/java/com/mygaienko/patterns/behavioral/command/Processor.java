package com.mygaienko.patterns.behavioral.command;

/**
 * Created by dmygaenko on 24/03/2016.
 */
public interface Processor<T, U> {
    U process(T t);
}
