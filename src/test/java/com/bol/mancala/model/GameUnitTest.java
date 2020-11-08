package com.bol.mancala.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ebru Ersoy Göksal
 */
class GameUnitTest {

    Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void play() {
        int selectedPitIndex = 1;
        game.play(selectedPitIndex);
        assertEquals(game.getBoard().getLastPit().getIndex(), 7);
        assertEquals(game.getBoard().getPits()[selectedPitIndex].getStoneCount(), 0);
        assertEquals(game.getCurrentPlayer().getId(), game.getPlayer2().getId());
    }

    @Test
    void should_get_opponent_of_player2(){
        game.setCurrentPlayer(game.getPlayer2());
        assertEquals(game.getOpponent().getId(), game.getPlayer1().getId());
    }
    @Test
    void should_get_opponent_of_player1(){
        game.setCurrentPlayer(game.getPlayer1());
        assertEquals(game.getOpponent().getId(), game.getPlayer2().getId());
    }
}