package com.bol.mancala.model.state;

import com.bol.mancala.model.Game;
import com.bol.mancala.util.PlayerType;

/**
 * @author Ebru Ersoy Göksal
 */
public class WhoIsNextState implements IState{
    @Override
    public void handle(Game context, int selectedIndex) {

        if(context.getBoard().getLastPit().getIndex() == context.getCurrentPlayer().getHouseIndex()){
            ContinueGameState continueGameState = new ContinueGameState();
            context.setCurrentState(continueGameState);
            context.setNextPlayerType(PlayerType.CURRENT);
            continueGameState.handle(context,selectedIndex);
        } else if(context.getBoard().getLastPit().getStoneCount() == 1
                && context.getBoard().getLastPit().getOwner().getId() == context.getCurrentPlayer().getId()
                && !context.getBoard().getLastPit().isHouse()){
            CaptureStoneState captureStoneState = new CaptureStoneState();
            context.setCurrentState(captureStoneState);
            context.setNextPlayerType(PlayerType.OPPONENT);
            captureStoneState.handle(context,selectedIndex);
        } else{
            ContinueGameState continueGameState = new ContinueGameState();
            context.setCurrentState(continueGameState);
            context.setNextPlayerType(PlayerType.OPPONENT);
            continueGameState.handle(context,selectedIndex);
        }

    }
}
