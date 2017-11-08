package com.mygaienko.patterns.behavioral.observer;

/**
 * Created by enda1n on 08.11.2017.
 */
public class AObserver implements Observer {

    @Override
    public void execute(Context context) {
        context.setValue("A");
    }
}
