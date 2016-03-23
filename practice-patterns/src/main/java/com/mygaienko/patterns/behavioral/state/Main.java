package com.mygaienko.patterns.behavioral.state;

/**
 * Created by dmygaenko on 23/03/2016.
 */
public class Main {

    public static void main(String[] args) {
        State aState = new AState();
        State bState = new BState();
        State cState = new CState();

        aState.setNextState(bState);
        bState.setNextState(cState);
        cState.setNextState(aState);

        Context context = new Context();
        context.setNextState(aState);

        for(int i = 0; i < 10; i++) {
            context.getNextState().execute(context);
        }
    }
}
