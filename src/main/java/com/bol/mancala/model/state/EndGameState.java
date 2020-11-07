package com.bol.mancala.model.state;

import com.bol.mancala.model.Board;
import com.bol.mancala.model.Game;
import com.bol.mancala.model.Player;

/**
 * @author Ebru Ersoy Göksal
 */
public class EndGameState extends State {
    @Override
    public void execute(Game context, int selectedIndex) {
        collectAllStonesToHouse(context, Board.houseIndexOfPlayer1);
        collectAllStonesToHouse(context, Board.houseIndexOfPlayer2);
        calculateWinner(context);
        context.setEnded(true);
    }

    private void calculateWinner(Game context) {
        int stoneCountPlayer1 = context.getBoard().getPits()[Board.houseIndexOfPlayer1].getStoneCount();
        int stoneCountPlayer2 = context.getBoard().getPits()[Board.houseIndexOfPlayer2].getStoneCount();
        context.setWinner(stoneCountPlayer1 > stoneCountPlayer2 ? context.getPlayer1().getName() : context.getPlayer2().getName());
    }

    private void collectAllStonesToHouse(Game context, int houseIndex) {
        int total = 0;
        for (int i = houseIndex - Player.pitCountForEachPlayer; i < houseIndex; i++){
            total += context.getBoard().getPits()[i].empty();
        }
        context.getBoard().getPits()[houseIndex].addStones(total);
    }
}
