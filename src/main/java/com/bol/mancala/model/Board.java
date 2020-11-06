package com.bol.mancala.model;

import com.bol.mancala.util.Constants;

/**
 * @author Ebru Ersoy Göksal
 */

public class Board {
    private final Pit[] pits;
    private Pit lastPit;

    public Board(Player player1, Player player2) {
        pits = new Pit[Constants.totalPits];
        createPits(player1);
        createPits(player2);
    }

    private void createPits(Player player) {
        for(int i = player.getStartIndex(); i <= player.getEndIndex(); i++){
            pits[i] = new Pit(i, player);
        }
        pits[player.getHouseIndex()] = new House(player,player.getHouseIndex());
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
