package com.bol.mancala.model.state;

import com.bol.mancala.model.Game;
import com.bol.mancala.model.Pit;

/**
 * @author Ebru Ersoy Göksal
 */
public class CaptureStoneState extends State{
    @Override
    public void execute(Game context, int selectedIndex) {
        captureStones(context);
        ContinueGameState continueGameState = new ContinueGameState();
        context.setCurrentState(continueGameState);
    }

    private void captureStones(Game context) {
        Pit lastPit = context.getBoard().getLastPit();
        Pit[] pits = context.getBoard().getPits();
        pits[lastPit.getOwner().getHouseIndex()].addStones(pits[lastPit.getOpponentPitIndexForCapture()].empty() + lastPit.empty());
    }


}
