package com.mygaienko.patterns.behavioral.chainofresponsibility.interfaces;

import com.mygaienko.patterns.behavioral.chainofresponsibility.Context;

/**
 * Created by dmygaenko on 25/03/2016.
 */
public interface Handler {

    void handle(Context context);
}
