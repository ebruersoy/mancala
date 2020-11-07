package com.bol.mancala.model.state;

import com.bol.mancala.model.Game;
import com.bol.mancala.util.PlayerType;

/**
 * @author Ebru Ersoy Göksal
 */
public class WhoIsNextState extends State {
    @Override
    public void execute(Game context, int selectedIndex) {
        if(context.getBoard().getLastPit().getIndex() == context.getCurrentPlayer().getHouseIndex()){
            ContinueGameState continueGameState = new ContinueGameState();
            context.setCurrentState(continueGameState);
            context.setNextPlayerType(PlayerType.CURRENT);
        } else if(context.getBoard().getLastPit().getStoneCount() == 1
                && context.getBoard().getLastPit().getOwner().getId() == context.getCurrentPlayer().getId()
                && !context.getBoard().getLastPit().isHouse()){
            CaptureStoneState captureStoneState = new CaptureStoneState();
            context.setCurrentState(captureStoneState);
            context.setNextPlayerType(PlayerType.OPPONENT);
        } else{
            ContinueGameState continueGameState = new ContinueGameState();
            context.setCurrentState(continueGameState);
            context.setNextPlayerType(PlayerType.OPPONENT);
        }

    }
}
