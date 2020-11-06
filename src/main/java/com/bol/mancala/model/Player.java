package com.bol.mancala.model;

import com.bol.mancala.util.Constants;

/**
 * @author Ebru Ersoy Göksal
 */

public class Player {
    private final int id;
    private final String name;
    private final int houseIndex;
    private final int startIndex;
    private final int endIndex;

    public Player(int startIndex, String name) {
        this.id = startIndex;
        this.name = name;
        this.startIndex = startIndex;
        this.endIndex = startIndex + Constants.pitCountForEachPlayer - 1;
        this.houseIndex = endIndex + 1;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHouseIndex() {
        return houseIndex;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

}
