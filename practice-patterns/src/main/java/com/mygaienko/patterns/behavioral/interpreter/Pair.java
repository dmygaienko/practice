package com.mygaienko.patterns.behavioral.interpreter;

/**
 * Created by dmygaenko on 19/05/2016.
 */
class Pair<A, B> {
    private final A left;
    private final B right;

    public Pair(A l, B r) {
        left = l;
        right = r;
    }

    public A left() {
        return left;
    }

    public B right() {
        return right;
    }
}
