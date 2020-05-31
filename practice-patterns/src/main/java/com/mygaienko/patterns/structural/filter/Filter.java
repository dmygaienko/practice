package com.mygaienko.patterns.structural.filter;

/**
 * Created by dmygaenko on 13/10/2016.
 */
public interface Filter<T> {

    T filtrate(T t);
}
