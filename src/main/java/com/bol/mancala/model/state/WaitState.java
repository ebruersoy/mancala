package com.bol.mancala.model.state;

import com.bol.mancala.exception.PreconditionFailedException;
import com.bol.mancala.model.Game;

/**
 * @author Ebru Ersoy Göksal
 */
public class WaitState implements IState {
    @Override
    public void handle(Game context, int selectedIndex) {
        if(checkForRules(context, selectedIndex)){
            SowState sowState = new SowState();
            context.setCurrentState(sowState);
            sowState.handle(context,selectedIndex);
        }
    }
    public boolean checkForRules(Game context, Integer pitId) {
        if(context.getBoard().getPits()[pitId].getStoneCount() == 0){
            throw new PreconditionFailedException("Empty Pits can not be selected!", context);
        }
        if(context.getBoard().getPits()[pitId].isHouse()){
            throw new PreconditionFailedException("House Pits can not be selected!", context);
        }
        if(context.getBoard().getPits()[pitId].getOwner().getId() != context.getCurrentPlayer().getId()){
            throw new PreconditionFailedException("Players can not select opponent's pits!", context);
        }
        return true;
    }
}
