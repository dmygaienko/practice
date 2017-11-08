package com.mygaienko.patterns.behavioral.observer;

import java.util.List;

/**
 * Created by enda1n on 08.11.2017.
 */
public class Service {

    private List<Observer> observers;

    public Service(List<Observer> observers) {
        this.observers = observers;
    }

    public void execute(Context context) {
        observers.forEach(observer -> observer.execute(context));
    }
}
