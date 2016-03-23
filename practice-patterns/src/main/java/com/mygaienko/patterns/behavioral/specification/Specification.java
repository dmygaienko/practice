package com.mygaienko.patterns.behavioral.specification;

/**
 * Created by dmygaenko on 23/03/2016.
 */
public interface Specification<T> {
    boolean isSatisfiedBy(T t);

    void specify(Context context);
}
