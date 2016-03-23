package com.mygaienko.patterns.behavioral.state;

/**
 * Created by dmygaenko on 23/03/2016.
 */
public class CState implements State {
    private State nextState;

    @Override
    public void execute(Context context) {
        System.out.println("CState processing");
        context.setNextState(nextState);
    }

    @Override
    public void setNextState(State nextState) {
        this.nextState = nextState;
    }
}
