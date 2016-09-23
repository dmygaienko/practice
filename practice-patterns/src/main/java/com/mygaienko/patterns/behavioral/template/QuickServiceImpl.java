package com.mygaienko.patterns.behavioral.template;

/**
 * Created by dmygaenko on 23/09/2016.
 */
public class QuickServiceImpl<T> extends AbstractService<T> {

    @Override
    public void init(T t) {
        System.out.println("quick init");
    }

    @Override
    public void doExecute(T t) {
        System.out.println("quick doExecute");
    }

    @Override
    public void destroy(T t) {
        System.out.println("quick destroy");
    }
}
