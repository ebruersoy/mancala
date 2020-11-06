package com.bol.mancala.model;

/**
 * @author Ebru Ersoy Göksal
 */

public class House extends Pit{

    public House(Player owner, int index) {
        super(index,owner);
        this.setStoneCount(0);
    }

    @Override
    public boolean isHouse() {
        return true;
    }
}
