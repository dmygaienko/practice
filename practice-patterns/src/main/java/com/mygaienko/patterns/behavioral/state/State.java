package com.mygaienko.patterns.behavioral.state;

/**
 * Created by dmygaenko on 23/03/2016.
 */
public interface State {

    void execute(Context context);

    void setNextState(State state);
}
