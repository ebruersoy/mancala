package com.bol.mancala.model.state;

import com.bol.mancala.model.Game;
import com.bol.mancala.model.Pit;

/**
 * @author Ebru Ersoy Göksal
 */
public class CaptureStoneState implements IState{
    @Override
    public void handle(Game context, int selectedIndex) {
        captureStones(context);
        ContinueGameState continueGameState = new ContinueGameState();
        context.setCurrentState(continueGameState);
        continueGameState.handle(context,selectedIndex);
    }

    private void captureStones(Game context) {
        Pit lastPit = context.getBoard().getLastPit();
        Pit[] pits = context.getBoard().getPits();
        pits[lastPit.getOwner().getHouseIndex()].addStone(pits[lastPit.getOpponentPitIndexForCapture()].emptyPit() + lastPit.emptyPit());
    }


}
