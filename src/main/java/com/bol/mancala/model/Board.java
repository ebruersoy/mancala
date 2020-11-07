package com.bol.mancala.model;

import com.bol.mancala.model.state.House;

/**
 * @author Ebru Ersoy Göksal
 */

public class Board {
    public final static int totalPits = 14;
    public final static int startIndexOfPlayer1 = 0;
    public final static int startIndexOfPlayer2 = 7;
    public final static int houseIndexOfPlayer1 = 6;
    public final static int houseIndexOfPlayer2 = 13;
    public final static int initialStoneCountForEachPit = 6;

    private final Pit[] pits;
    private Pit lastPit;

    public Board(Player player1, Player player2) {
        pits = new Pit[totalPits];
        createPits(player1);
        createPits(player2);
    }

    private void createPits(Player player) {
        for(int i = player.getStartIndex(); i <= player.getEndIndex(); i++){
            pits[i] = new Pit(i, player);
        }
        pits[player.getHouseIndex()] = new House(player.getHouseIndex(), player);
    }

    public Pit[] getPits() {
        return pits;
    }

    public Pit getLastPit() {
        return lastPit;
    }

    public void setLastPit(Pit lastPit) {
        this.lastPit = lastPit;
    }
}
