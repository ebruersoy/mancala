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
        game.play(0);
        assertEquals(game.getBoard().getLastPit().getIndex(), 5);
        assertEquals(game.getBoard().getPits()[0].getStoneCount(), 1);
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