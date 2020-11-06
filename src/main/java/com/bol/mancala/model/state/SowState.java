package com.bol.mancala.model.state;

import com.bol.mancala.model.Game;
import com.bol.mancala.model.Pit;
import com.bol.mancala.model.Player;

/**
 * @author Ebru Ersoy Göksal
 */
public class SowState implements IState {
    @Override
    public void handle(Game context, int selectedIndex) {
        sow(context, context.getCurrentPlayer(), selectedIndex);
        WhoIsNextState whoIsNextState = new WhoIsNextState();
        context.setCurrentState(whoIsNextState);
        whoIsNextState.handle(context, selectedIndex);
    }

    private void sow(Game context, Player currentPlayer, int selectedPitIndex){
        Pit selectedPit = context.getBoard().getPits()[selectedPitIndex];
        int stoneCount = selectedPit.selected();
        int nextPitId = selectedPit.nextPitId();

        while (stoneCount != 0){
            Pit nextPit = context.getBoard().getPits()[nextPitId];
            if(nextPit.isHouse() && nextPit.getOwner().getId() != currentPlayer.getId()){ //opponent's house is forbidden!
                nextPitId = nextPit.nextPitId();
                continue;
            }
            nextPit.addStone(1);
            stoneCount--;
            context.getBoard().setLastPit(nextPit);
            nextPitId = nextPit.nextPitId();
        }
    }
}
