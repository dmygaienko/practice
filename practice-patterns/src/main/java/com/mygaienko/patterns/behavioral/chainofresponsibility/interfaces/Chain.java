package com.mygaienko.patterns.behavioral.chainofresponsibility.interfaces;

/**
 * Created by dmygaenko on 25/03/2016.
 */
public interface Chain<T> {

    T getNext();
    void setNext(T t);
}
