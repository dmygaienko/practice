package com.mygaienko.patterns.behavioral.state;

/**
 * Created by dmygaenko on 23/03/2016.
 */
public class Context {
    private State nextState;

    public State getNextState() {
        return nextState;
    }

    public void setNextState(State nextState) {
        this.nextState = nextState;
    }


}
