package com.mygaienko.patterns.behavioral.state;

/**
 * Created by dmygaenko on 23/03/2016.
 */
public class AState implements State {
    private State nextState;

    @Override
    public void execute(Context context) {
        System.out.println("AState processing");
        context.setNextState(nextState);
    }

    @Override
    public void setNextState(State nextState) {
        this.nextState = nextState;
    }
}
