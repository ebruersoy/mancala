package com.bol.mancala.model.state;

import com.bol.mancala.exception.PreconditionFailedException;
import com.bol.mancala.model.Game;

/**
 * @author Ebru Ersoy Göksal
 */
public class WaitState extends State {
    @Override
    public void execute(Game context, int selectedIndex) {
        if(checkForRules(context, selectedIndex)){
            SowState sowState = new SowState();
            context.setCurrentState(sowState);
        }
    }
    private boolean checkForRules(Game context, Integer pitIndex) {
        if(context.getBoard().getPits()[pitIndex].isHouse()){
            throw new PreconditionFailedException("House Pits can not be selected!", context);
        }
        if(context.getBoard().getPits()[pitIndex].getOwner().getId() != context.getCurrentPlayer().getId()){
            throw new PreconditionFailedException("Players can not select opponent's pits!", context);
        }
        if(context.getBoard().getPits()[pitIndex].getStoneCount() == 0){
            throw new PreconditionFailedException("Empty Pits can not be selected!", context);
        }
        return true;
    }
}
