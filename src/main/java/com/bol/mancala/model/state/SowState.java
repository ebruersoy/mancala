package com.bol.mancala.model.state;

import com.bol.mancala.model.Game;
import com.bol.mancala.model.Pit;
import com.bol.mancala.model.Player;

/**
 * @author Ebru Ersoy Göksal
 */
public class SowState extends State {
    @Override
    public void execute(Game context, int selectedIndex) {
        sow(context, context.getCurrentPlayer(), selectedIndex);
        WhoIsNextState whoIsNextState = new WhoIsNextState();
        context.setCurrentState(whoIsNextState);
    }

    private void sow(Game context, Player currentPlayer, int selectedPitIndex){
        Pit selectedPit = context.getBoard().getPits()[selectedPitIndex];
        int stoneCount = selectedPit.select();
        int nextPitIndex = selectedPit.nextPitIndex();

        while (stoneCount != 0){
            Pit nextPit = context.getBoard().getPits()[nextPitIndex];
            if(nextPit.isHouse() && nextPit.getOwner().getId() != currentPlayer.getId()){ //opponent's house is forbidden!
                nextPitIndex = nextPit.nextPitIndex();
                continue;
            }
            nextPit.addStones(1);
            stoneCount--;
            context.getBoard().setLastPit(nextPit);
            nextPitIndex = nextPit.nextPitIndex();
        }
    }
}
