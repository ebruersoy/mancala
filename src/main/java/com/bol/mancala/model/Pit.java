package com.bol.mancala.model;

/**
 * @author Ebru Ersoy Göksal
 */

public class Pit{
    private int index;
    private final Player owner;
    private int stoneCount;

    public int nextPitIndex(){
        return (index + 1) % Board.totalPits;
    }

    public Pit(int index, Player owner) {
        this.index = index;
        this.owner = owner;
        this.stoneCount = Board.initialStoneCountForEachPit;
    }

    public void addStones(int count) {
        stoneCount += count;
    }

    public int select() {
        int currentStoneCount = stoneCount;
        stoneCount = 0;
        return currentStoneCount;
    }

    public int empty(){
        int currentStoneCount = stoneCount;
        stoneCount = 0;
        return currentStoneCount;
    }

    public int getOpponentPitIndexForCapture() {
        int difference = getOwner().getHouseIndex() - index;
        return (getOwner().getHouseIndex() + difference ) % Board.totalPits;
    }

    public int getIndex() {
        return index;
    }

    public Player getOwner() {
        return owner;
    }

    public int getStoneCount() {
        return stoneCount;
    }

    public boolean isHouse() {
        return false;
    }

    public void setStoneCount(int stoneCount) {
        this.stoneCount = stoneCount;
    }
}
