package com.bol.mancala.model.state;

import com.bol.mancala.model.Game;

/**
 * @author Ebru Ersoy Göksal
 */
public interface IState {
    void handle(Game context, int selectedIndex);
}
