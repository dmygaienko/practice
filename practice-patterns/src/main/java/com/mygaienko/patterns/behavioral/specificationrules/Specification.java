package com.mygaienko.patterns.behavioral.specificationrules;

/**
 * Created by dmygaenko on 23/03/2016.
 */
public interface Specification<T> {

    boolean isSatisfiedBy(T t);

    void specify(T t, Context context);
}
