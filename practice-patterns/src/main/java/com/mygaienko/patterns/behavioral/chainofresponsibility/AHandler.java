package com.mygaienko.patterns.behavioral.chainofresponsibility;

import com.mygaienko.patterns.behavioral.chainofresponsibility.interfaces.Chain;
import com.mygaienko.patterns.behavioral.chainofresponsibility.interfaces.Handler;

/**
 * Created by dmygaenko on 25/03/2016.
 */
public class AHandler implements Handler, Chain<Handler> {

    private Handler next;

    @Override
    public Handler getNext() {
        return next;
    }

    @Override
    public void setNext(Handler handler) {
        next = handler;
    }

    @Override
    public void handle(Context context) {
        System.out.println("A handles context");

        if (next != null) {
            next.handle(context);
        }
    }
}
