package com.bol.mancala.model.state;

import com.bol.mancala.model.Game;

/**
 * @author Ebru Ersoy Göksal
 */
public abstract class State implements IState {

    @Override
    public void handle(Game context, int selectedPitIndex) {
        execute(context,selectedPitIndex);
        if(!(context.getCurrentState() instanceof WaitState)) {
            doNext(context, selectedPitIndex);
        }
    }

    protected final void doNext(Game context, int selectedPitIndex){
        context.getCurrentState().handle(context, selectedPitIndex);
    }

    protected abstract void execute(Game context, int selectedPitIndex);

}
