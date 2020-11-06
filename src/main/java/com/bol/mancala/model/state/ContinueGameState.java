package com.bol.mancala.model.state;

import com.bol.mancala.model.Game;
import com.bol.mancala.model.Player;
import com.bol.mancala.util.PlayerType;

/**
 * @author Ebru Ersoy Göksal
 */
public class ContinueGameState implements IState{
    @Override
    public void handle(Game context, int selectedIndex) {
        if (canGameEnd(context)){
            EndGameState endGameState = new EndGameState();
            context.setCurrentState(endGameState);
            endGameState.handle(context,selectedIndex);
        } else{
            WaitState waitState = new WaitState();
            context.setCurrentState(waitState);
            setPlayerTurn(context);
        }
    }

    private boolean canGameEnd(Game context) {
        return isAllPitsOfPlayerEmpty(context, context.getPlayer1()) || isAllPitsOfPlayerEmpty(context, context.getPlayer2());
    }

    private boolean isAllPitsOfPlayerEmpty(Game context, Player player) {
        for(int i = player.getStartIndex(); i <= player.getEndIndex(); i++){
            if(context.getBoard().getPits()[i].getStoneCount() != 0){
                return false;
            }
        }
        return true;
    }

    private void setPlayerTurn(Game context) {
        if(context.getNextPlayerType() == PlayerType.OPPONENT){
            context.setCurrentPlayer(context.getOpponent());
        }
    }
}
