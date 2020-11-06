package com.bol.mancala.model.state;

import com.bol.mancala.model.Game;
import com.bol.mancala.util.Constants;

/**
 * @author Ebru Ersoy Göksal
 */
public class EndGameState implements IState{
    @Override
    public void handle(Game context, int selectedIndex) {
        collectAllStonesToHouse(context, Constants.houseIndexOfPlayer1);
        collectAllStonesToHouse(context, Constants.houseIndexOfPlayer2);
        calculateWinner(context);
        context.setEnded(true);
    }

    private void calculateWinner(Game context) {
        int stoneCountPlayer1 = context.getBoard().getPits()[Constants.houseIndexOfPlayer1].getStoneCount();
        int stoneCountPlayer2 = context.getBoard().getPits()[Constants.houseIndexOfPlayer2].getStoneCount();
        context.setWinner(stoneCountPlayer1 > stoneCountPlayer2 ? context.getPlayer1().getName() : context.getPlayer2().getName());
    }

    private void collectAllStonesToHouse(Game context, int houseIndex) {
        int total = 0;
        for (int i = houseIndex - Constants.pitCountForEachPlayer; i < houseIndex; i++){
            total += context.getBoard().getPits()[i].emptyPit();
        }
        context.getBoard().getPits()[houseIndex].addStone(total);
    }
}
