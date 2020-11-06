package com.bol.mancala.model;

import com.bol.mancala.util.Constants;

/**
 * @author Ebru Ersoy Göksal
 */

public class Pit{
    private int index;
    private final Player owner;
    private int stoneCount;

    public int nextPitId(){
        return (index + 1) % Constants.totalPits;
    }

    public Pit(int index, Player owner) {
        this.index = index;
        this.owner = owner;
        stoneCount = Constants.initialStoneCountForEachPit;
    }

    public void addStone(int count) {
        stoneCount += count;
    }

    public int selected() {
        if(stoneCount == 1){
            stoneCount = 0;
            return 1;
        }
        int currentStoneCount = stoneCount;
        stoneCount = 1;
        int removedStoneCount = currentStoneCount - 1;
        return removedStoneCount;
    }

    public int emptyPit(){
        int currentStoneCount = stoneCount;
        stoneCount = 0;
        return currentStoneCount;
    }

    public int getOpponentPitIndexForCapture() {
        int difference = getOwner().getHouseIndex() - index;
        int indexToBeCaptured = (getOwner().getHouseIndex() + difference ) % Constants.totalPits;
        return indexToBeCaptured;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isHouse() {
        return false;
    }

    public Player getOwner() {
        return owner;
    }

    public int getStoneCount() {
        return stoneCount;
    }

    public void setStoneCount(int stoneCount) {
        this.stoneCount = stoneCount;
    }

}
