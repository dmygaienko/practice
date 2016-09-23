package com.mygaienko.patterns.behavioral.template;

/**
 * Created by dmygaenko on 23/09/2016.
 */
public class SlowServiceImpl<T> extends AbstractService<T> {

    @Override
    public void init(T t) {
        System.out.println("slow init");
    }

    @Override
    public void doExecute(T t) {
        System.out.println("slow doExecute");
    }

    @Override
    public void destroy(T t) {
        System.out.println("slow destroy");
    }
}
