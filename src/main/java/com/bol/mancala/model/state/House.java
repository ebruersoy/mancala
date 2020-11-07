package com.bol.mancala.model.state;

import com.bol.mancala.model.Pit;
import com.bol.mancala.model.Player;

/**
 * @author Ebru Ersoy Göksal
 */

public class House extends Pit {

    public House(int index, Player owner) {
        super(index,owner);
        this.setStoneCount(0);
    }

    @Override
    public boolean isHouse() {
        return true;
    }
}
