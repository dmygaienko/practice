package com.mygaienko.patterns.behavioral.template;

/**
 * Created by dmygaenko on 23/09/2016.
 */
abstract class AbstractService<T> {

    public void execute(T t) {
        init(t);
        doExecute(t);
        destroy(t);
    }

    public abstract void init(T t);
    public abstract void doExecute(T t);
    public abstract void destroy(T t);


}
