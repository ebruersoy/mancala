package com.bol.mancala.model.state;

import com.bol.mancala.model.Board;
import com.bol.mancala.model.Game;
import com.bol.mancala.model.Pit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;

/**
 * @author Ebru Ersoy Göksal
 */
@Tag("UnitTest")
class EndGameStateUnitTest {

    Game game;
    EndGameState endGameState;

    @BeforeEach
    void setUp() {
        endGameState = new EndGameState();
        game = new Game();
        game.setCurrentState(endGameState);
    }

    @Test
    void should_game_ended(){
        endGameState.execute(game, anyInt());
        assertTrue(game.isEnded());
    }

    @Test
    void player_1_should_be_winner(){
        Pit[] pits = game.getBoard().getPits();
        pits[0].setStoneCount(2);
        pits[1].setStoneCount(3);
        pits[2].setStoneCount(4);
        pits[3].setStoneCount(0);
        pits[4].setStoneCount(0);
        pits[5].setStoneCount(1);
        pits[6].setStoneCount(30);
        pits[7].setStoneCount(0);
        pits[8].setStoneCount(0);
        pits[9].setStoneCount(0);
        pits[10].setStoneCount(0);
        pits[11].setStoneCount(0);
        pits[12].setStoneCount(0);
        pits[13].setStoneCount(32);
        endGameState.handle(game, anyInt());
        assertEquals(game.getWinner(), game.getPlayer1().getName());
        assertEquals(game.getBoard().getPits()[Board.houseIndexOfPlayer1].getStoneCount(), 40);
        assertEquals(game.getBoard().getPits()[Board.houseIndexOfPlayer2].getStoneCount(), 32);
    }

}