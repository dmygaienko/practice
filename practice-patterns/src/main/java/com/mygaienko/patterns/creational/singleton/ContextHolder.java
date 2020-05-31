package com.mygaienko.patterns.creational.singleton;

/**
 * Created by dmygaenko on 06/05/2016.
 */
public enum ContextHolder {

    INSTANCE;

    private ThreadLocal<Context> threadLocal = new ThreadLocal<Context>();

    public void setContext(Context context) {
        threadLocal.set(context);
    }

    public Context getContext() {
        return threadLocal.get();
    }
}
